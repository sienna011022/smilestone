export default async function createWebRtcTransport(router, callback) {
    try {
        const webRtcTransport_options = {
            listenIps: [
                {
                    ip: '43.200.154.60', // replace with relevant IP address
                }
            ],
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