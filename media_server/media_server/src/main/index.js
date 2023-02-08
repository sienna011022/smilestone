import express from 'express';
import http from 'http';
import { Server } from "socket.io";
import { createWorker } from "mediasoup";
import {
    Level4_1,
    ProfileConstrainedHigh,
    ProfileHigh,
    profileLevelIdToString,
    ProfileMain
} from "h264-profile-level-id";

const app = express();
const server = http.createServer(app);
const io = new Server(server, {
    cors: {
        origin: "*"
    }
});

const client = io.of('/mediasoup');
const worker = await createWorker()
const routers = new Map()
let producerTransports = new Map()
let consumerTransports = new Map()
let producers = new Map()
let consumers = new Map()
client.on('connection', async (socket) => {
    socket.on("get-rtpCapabilities",  async (viewer, streamer, callback) => {
        console.log(viewer, "is trying to start viewing to", streamer)

        const rtpCapabilities = routers.get(streamer).rtpCapabilities
        callback({ rtpCapabilities })
    })

    socket.on("create-recv-webRtc-transport", async (viewer, streamer, callback) => {
        console.log("Creating receiving transport")
        try {
            const webRtcTransport_options = {
                listenIps: [ { ip: "172.31.11.132", announcedIp: "43.200.154.60" } ],
                enableUdp: true,
                enableTcp: true,
                preferUdp: true,
            }

            // https://mediasoup.org/documentation/v3/mediasoup/api/#router-createWebRtcTransport
            let transport = await routers.get(streamer).createWebRtcTransport(webRtcTransport_options)

            transport.on('dtlsstatechange', dtlsState => {
                if (dtlsState === 'closed') {
                    transport.close()
                }
            })

            transport.on('close', () => {
                console.log('transport closed')
            })

            // send back to the client the following prameters
            callback({
                // https://mediasoup.org/documentation/v3/mediasoup-client/api/#TransportOptions
                params: {
                    id: transport.id,
                    iceParameters: transport.iceParameters,
                    iceCandidates: transport.iceCandidates,
                    dtlsParameters: transport.dtlsParameters,
                }
            })

            consumerTransports.set(streamer + viewer, transport)
        } catch (error) {
            console.log(error)
            callback({
                params: {
                    error: error
                }
            })
        }
    })

    socket.on('transport-recv-connect', async (viewer, streamer, { dtlsParameters }) => {
        console.log(viewer, "is connecting to", streamer)
        await consumerTransports.get(streamer + viewer).connect({ dtlsParameters })
    })

    socket.on('consume', async (viewer, streamer, { rtpCapabilities }, callback) => {
        console.log(viewer, streamer, "consuming")
        try {
            // check if the router can consume the specified producer
                // transport can now consume and return a consumer
            const videoConsumer = await consumerTransports.get(streamer + viewer).consume({
                producerId: producers.get(streamer + "video").id,
                rtpCapabilities,
                paused: true,
            })

            const audioConsumer = await consumerTransports.get(streamer + viewer).consume({
                producerId: producers.get(streamer + "audio").id,
                rtpCapabilities,
                paused: true,
            })

            videoConsumer.on('transportclose', () => {
                console.log('transport close from consumer')
            })

            videoConsumer.on('producerclose', () => {
                console.log('producer of consumer closed')
            })

            audioConsumer.on('transportclose', () => {
                console.log('transport close from consumer')
            })

            audioConsumer.on('producerclose', () => {
                console.log('producer of consumer closed')
            })

            consumers.set(streamer + viewer + "video", videoConsumer)
            consumers.set(streamer + viewer + "audio", audioConsumer)

            // from the consumer extract the following params
            // to send back to the Client
            const params = {
                video : {
                    id: videoConsumer.id,
                    producerId: producers.get(streamer+"video").id,
                    kind: videoConsumer.kind,
                    rtpParameters: videoConsumer.rtpParameters,
                },
                audio : {
                    id: audioConsumer.id,
                    producerId: producers.get(streamer+"audio").id,
                    kind: audioConsumer.kind,
                    rtpParameters: audioConsumer.rtpParameters,
                }

            }

            // send the parameters to the client
            callback({ params })

        } catch (error) {
            console.log(error.message)
            callback({
                params: {
                    error: error
                }
            })
        }
    })

    socket.on('consumer-resume', async (viewer, streamer) => {
        console.log("consumer", streamer + viewer, "resume!!!")
        await consumers.get(streamer + viewer + "video").resume()
        await consumers.get(streamer + viewer + "audio").resume()
    })

    socket.on("start-and-get-rtpCapabilities",  async (streamer, callback) => {
        console.log(streamer, "is trying to start streaming")

        routers.set(streamer, await worker.createRouter({
            mediaCodecs: [
                {
                    kind        : "audio",
                    mimeType    : "audio/opus",
                    clockRate   : 48000,
                    channels    : 2
                },
                {
                    kind       : "video",
                    mimeType   : "video/H264",
                    clockRate  : 90000,
                    parameters : {
                        "packetization-mode"      : 1,
                        "profile-level-id"        : profileLevelIdToString({
                            level: Level4_1,
                            profile: ProfileHigh
                        }),
                        "level-asymmetry-allowed" : 1
                    }
                }
            ]
        }))

        const rtpCapabilities = routers.get(streamer).rtpCapabilities
        callback({ rtpCapabilities })
    })

    socket.on("create-webRTC-transport", async (streamer, callback) => {
        console.log(streamer, "is creating webRTC Transport")
        try {
            const webRtcTransport_options = {
                listenIps: [ { ip: "172.31.11.132", announcedIp: "43.200.154.60" } ],
                enableUdp: true,
                enableTcp: true,
                preferUdp: true,
            }

            // https://mediasoup.org/documentation/v3/mediasoup/api/#router-createWebRtcTransport
            let transport = await routers.get(streamer).createWebRtcTransport(webRtcTransport_options)

            transport.on('dtlsstatechange', dtlsState => {
                if (dtlsState === 'closed') {
                    transport.close()
                }
            })

            transport.on('close', () => {
                console.log('transport closed')
            })

            // send back to the client the following prameters
            callback({
                // https://mediasoup.org/documentation/v3/mediasoup-client/api/#TransportOptions
                params: {
                    id: transport.id,
                    iceParameters: transport.iceParameters,
                    iceCandidates: transport.iceCandidates,
                    dtlsParameters: transport.dtlsParameters,
                }
            })

            producerTransports.set(streamer, transport)
        } catch (error) {
            console.log(error)
            callback({
                params: {
                    error: error
                }
            })
        }
    })

    socket.on('send-transport-connect', async (streamer, { dtlsParameters }) => {
        console.log("connecting to ProducerTransport")
        await producerTransports.get(streamer).connect({ dtlsParameters })
    })

    socket.on('transport-produce', async (streamer, { kind, rtpParameters, appData }, callback) => {
        // call produce based on the prameters from the client
        const producer = await producerTransports.get(streamer).produce({
            kind,
            rtpParameters,
        })
        producers.set(streamer + kind, producer)

        producer.on('transportclose', () => {
            console.log('transport for this producer closed ')
            producer.close()
        })

        // Send back to the client the Producer's id
        callback({
            id: producer.id
        })
    })
})

server.listen(3000, () => {
    console.log('listening on *:3000');
});