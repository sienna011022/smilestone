import {io, Socket} from "socket.io-client";
import {Device} from "mediasoup-client";
import {useEffect, useState} from "react";
import {Transport} from "mediasoup-client/lib/Transport";
import ReactPlayer from "react-player";

const TestClient = () => {
  const [clientStream, setClientStream] = useState<MediaStream>()
  const [streamer, setStreamer] = useState("test")
  const [viewer, setViewer] = useState("testee")
  const [socket, setSocket] = useState<Socket>(io("43.200.154.60:3000/mediasoup"))

  const onClickView = () => {
    socket.emit("get-rtpCapabilities", viewer, streamer, async (data : any) => {
      const device = new Device()
      let consumerTransport : Transport;
      await device.load({
        routerRtpCapabilities: data.rtpCapabilities
      })

      socket.emit("create-recv-webRtc-transport", viewer, streamer, async ({params} : any) => {
        // The server sends back params needed
        // to create Send Transport on the client side
        if (params.error) {
          console.log(params.error)
          return
        }

        consumerTransport = device.createRecvTransport(params)

        consumerTransport.on('connect', async ({dtlsParameters}, callback, errback) => {
          try {
            await socket.emit('transport-recv-connect', viewer, streamer, {
              dtlsParameters,
            })
            callback()
          } catch (error : any) {
            errback(error)
          }
        })

        await socket.emit('consume', viewer, streamer, {
          rtpCapabilities: device.rtpCapabilities
        }, async ({params} : any) => {
          if (params.error) {
            console.log('Cannot Consume')
            return
          }

          const tracks = []
          const videoConsumer = await consumerTransport.consume({
            id: params["video"].id,
            producerId: params["video"].producerId,
            kind: params["video"].kind,
            rtpParameters: params["video"].rtpParameters
          })

          const soundConsumer = await consumerTransport.consume({
            id: params["audio"].id,
            producerId: params["audio"].producerId,
            kind: params["audio"].kind,
            rtpParameters: params["audio"].rtpParameters
          })
          await socket.emit('consumer-resume', viewer, streamer)

          tracks.push(soundConsumer.track, videoConsumer.track)
          console.log(tracks)
          setClientStream(new MediaStream(tracks))
        })
      })
    })
  }

  return (
    <>
      <button onClick={onClickView}>시청하기</button>
      {
        clientStream ?
          <ReactPlayer controls={true} width={"100%"} height={"830px"} url={clientStream} volume={1} muted={false} /> :
          <div>시작하기를 눌러주세요</div>
      }
    </>
  );
};

export default TestClient;
