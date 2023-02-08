export default async function createWebRtcTransport(router, callback) {
    console.log("creating WebRTC Transport")
    try {
        const webRtcTransport_options = {
            listenIps    : [ { ip: "172.31.11.132", announcedIp: "43.200.154.60" } ],
            enableUdp: true,
            enableTcp: true,
            preferUdp: true,
        }

        // https://mediasoup.org/documentation/v3/mediasoup/api/#router-createWebRtcTransport
        let transport = await router.createWebRtcTransport(webRtcTransport_options)

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

        return transport
    } catch (error) {
        console.log(error)
        callback({
            params: {
                error: error
            }
        })
    }
}