import {Device} from "mediasoup-client";
import {io, Socket} from "socket.io-client";
import {
  Level4,
  ProfileConstrainedHigh,
  ProfileHigh,
  profileLevelIdToString,
  ProfileMain
} from "h264-profile-level-id";
import {useState} from "react";
import ReactPlayer from "react-player";

const TestStreamer = () => {
  const [streamer, setStreamer] = useState<string>("test")
  const [testStream, setTestStream] = useState<MediaStream>()
  const [socket, setSocket] = useState<Socket>(io("43.200.154.60:3000/mediasoup"))

  const onClickStream =() => {
    navigator.mediaDevices.getDisplayMedia({
      video: {
        width: {
          max: 1280,
          ideal: 1280,
        },
        height: {
          max: 720,
          ideal: 720,
        }
      },
      audio: true
    }).then( async (stream) =>  {
      const soundStream = await navigator.mediaDevices.getUserMedia({
        video: false,
        audio: true
      })
      stream.addTrack(soundStream.getAudioTracks()[0])
      const videoTrack = stream.getVideoTracks()[0]
      const audioTrack = stream.getAudioTracks()[0]

      setTestStream(stream)

      const device : Device = new Device()
      socket.emit("start-and-get-rtpCapabilities", streamer, async (data : any) => {
        await loadDevice(device, data)
        if(device.canProduce("video")) {
          socket.emit("create-webRTC-transport", streamer, async ({params} : any) => {
            if (params.error) {
              console.log(params.error)
              return
            }
            const producerTransport = device.createSendTransport(params)
            producerTransport.on("connect", async ({dtlsParameters}, callback, errback) => {
              try {
                socket.emit("send-transport-connect", streamer, {dtlsParameters})
                callback()
              } catch (error) {
                // @ts-ignore
                errback(error)
              }
            })

            producerTransport.on("produce", (parameters, callback, errback) => {
              try {
                socket.emit("transport-produce", streamer, {
                  transportId: producerTransport.id,
                  kind: parameters.kind,
                  rtpParameters: parameters.rtpParameters,
                  appData: parameters.appData
                }, ({id} : any) => {
                  callback({id})
                })
              } catch (error : any) {
                errback(error)
              }
            })

            const videoProducer = await producerTransport.produce({
              track       : videoTrack,
              encodings   :
                [
                  { maxBitrate: 900000 }
                ],
              codecOptions :
                {
                  videoGoogleStartBitrate : 4000
                }
            })
            videoProducer.on('trackended', () => {
              console.log('track ended')
              // close video track
            })

            videoProducer.on('transportclose', () => {
              console.log('transport ended')
              // close video track
            })
            const soundProducer = await producerTransport.produce({
              track       : audioTrack,
              codec: {
                kind: "audio",
                mimeType: "audio/opus",
                clockRate: 48000,
                channels    : 2
              }
            })
            soundProducer.on('trackended', () => {
              console.log('track ended')
              // close video track
            })

            soundProducer.on('transportclose', () => {
              console.log('transport ended')
              // close video track
            })
          })
        } else {
          console.log("FUFUFFFFFFFFFFFFFFFFFFFFFFFUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUCCCCCCCCCCCCCCCKKKKKKKKKK")
        }
      })
    })
  }

  async function loadDevice(device : Device, data : any) {
    await device.load({
      routerRtpCapabilities: data.rtpCapabilities
    })

    if(!device.canProduce("video")) {
      console.warn("cannot produce video")
    }
  }
  return (
    <>
      <button onClick={onClickStream}>방송시작</button>
      {
        testStream ?
          <ReactPlayer controls={true} width={"100%"} height={"830px"} url={testStream} volume={1} muted={false}/> :
          <div>방송 시작 버튼을 눌러 주세요!</div>
      }
    </>
  );
};

export default TestStreamer;
