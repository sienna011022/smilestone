import SockJS from "sockjs-client"
import Stomp from "stompjs"
import {ChangeEvent, useEffect, useState} from "react";

export default function TestChat() {
  const [state, setState] = useState("close")
  const [username, setUsername] = useState("tester1")
  const [roomId, setRoomId] = useState("0")
  const [message, setMessage] = useState("")
  const [messageHistory, setMessageHistory] = useState<any>([])
  const [stompClient, setStompClient] = useState<Stomp.Client>(Stomp.over(new SockJS("http://3.34.86.115:8090/smilestone/chat")))

  useEffect( () => {
    stompClient.connect({}, (frame : any) => {}, (e: any) => {});
  }, [])

  const onClickTestChatRoom = (sellerName: string) => () => {
    stompClient.subscribe(`/chat/${roomId}`, (msg: any) => {
      const jsonFrame = JSON.parse(msg.body)
      setMessageHistory((prev: any) => [...prev, {sender: jsonFrame.sender, message: jsonFrame.message, chatAt: jsonFrame.chatAt}])
    });
  }
  const onChangeMessage = (e: ChangeEvent<HTMLInputElement>) => {
    setMessage(e.target.value)
  }
  const onClickSendMessage = () => {
    stompClient.send(`/pub/chat.${roomId}`, {}, JSON.stringify({roomId: roomId, sender: username, message: message, chatAt: new Date().toISOString()}))
  }

  return (
    <>
      <div>{state}</div>
      <div>
        <button onClick={onClickTestChatRoom("tester2")}>join test chat room</button>
        <input type={"text"} onChange={onChangeMessage} value={message}/>
        <button onClick={onClickSendMessage}>send</button>
        {
          messageHistory.map((v: any, i: number) => (
            <div key={i}>{v.sender + " : " + v.message + " / " + v.chatAt}</div>
          ))
        }
      </div>
    </>
  )
}
