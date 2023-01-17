import express from 'express';
import http from 'http';
import { Server } from "socket.io";
import {createWorker} from "mediasoup";
import media_codec from "./config/media_codec.js";
import createWebRtcTransport from "./util/create_webRTC_transport.js";
import {Level4_1, ProfileConstrainedHigh, profileLevelIdToString} from "h264-profile-level-id";

const app = express();
const server = http.createServer(app);
const io = new Server(server, {
    cors: {
        origin: "*"
    }
});

const client = io.of('/mediasoup');
const worker = await createWorker()
client.on('connection', async (socket) => {
    const routers = new Map()
    let producerTransports = new Map()
    let consumerTransports = new Map()
    let producers = new Map()
    let consumers = new Map()

    socket.on("start-streaming", async (streamer) => {
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
                    parameters :
                        {
                            "packetization-mode": 1,
                            "profile-level-id": profileLevelIdToString({
                                level: Level4_1,
                                profile: ProfileConstrainedHigh
                            }),
                            "level-asymmetry-allowed" : 1
                        }
                }
            ]
        }))
    })

    socket.on("get-rtpCapabilities",  (streamer, callback) => {
        const rtpCapabilities = routers.get(streamer).rtpCapabilities
        callback({ rtpCapabilities })
    })

    socket.on("create-webRTC-transport", async (streamer, callback) => {
        producerTransports.set(streamer, await createWebRtcTransport(callback))
    })

    socket.on('send-transport-connect', async (streamer, { dtlsParameters }) => {
        await producerTransports.get(streamer).connect({ dtlsParameters })
    })

    socket.on('transport-produce', async (streamer, { kind, rtpParameters, appData }, callback) => {
        // call produce based on the prameters from the client
        const producer = await producerTransports.get(streamer).produce({
            kind,
            rtpParameters,
        })
        producers.set(streamer, producer)

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
