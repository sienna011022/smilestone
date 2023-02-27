package com.smilestone.smarket.stomp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.data.User
import com.smilestone.smarket.dto.Chat
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.LifecycleEvent
import java.time.LocalDateTime

object StompService {
    val url = "ws://3.34.86.115:8090/smilestone/chat/websocket" // 소켓에 연결하는 엔드포인트가 /socket일때 다음과 같음
    val stompClient =  Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)
    var roomNum: String? = null
    var response = 0



    fun runStomp(room: String, chatList: MutableLiveData<ArrayList<Chat>>, list: ArrayList<Chat>){
        roomNum = room
        stompClient.topic("/chat/${room}").subscribe { topicMessage ->
            val json = JSONObject(topicMessage.payload)
            Log.d("테스트 스톰프", json.toString())
            val chat: Chat = Chat(json.getString("roomId"), json.getString("sender"), json.getString("message"), json.getString("chatAt"))
            try{
                list.add(chat)
                chatList.postValue(list)
            }
            catch(e: Exception){
                //chatList.value?.add(chat)
            }
            Log.d("테스트 스톰프", chatList.value.toString())

        }


        stompClient.connect()

        stompClient.lifecycle().subscribe { lifecycleEvent ->
            when (lifecycleEvent.type) {
                LifecycleEvent.Type.OPENED -> {
                    Log.d("스톰프", "open")
                }
                LifecycleEvent.Type.CLOSED -> {
                    stompClient.send("/pub/chat.${roomNum}", "${User.nickname}님이 퇴장하셨습니다.").subscribe()
                }
                LifecycleEvent.Type.ERROR -> {
                    Log.d("스톰프", "에러")
                    Log.d("스톰프", lifecycleEvent.exception.toString())
                }
                else ->{
                    Log.d("스톰프", lifecycleEvent.message)
                }
            }
        }

        val data = JSONObject()
        data.put("roomId", roomNum)
        data.put("sender", User.nickname)
        data.put("message", "${User.nickname}님이 입장하셨습니다.t529tZ")
        data.put("chatAt", LocalDateTime.now().toString())

        stompClient.send("/pub/chat.${roomNum}", data.toString()).subscribe()
  }

    fun sendMessage(message: String){
        val data = JSONObject()
        data.put("roomId", roomNum)
        data.put("sender", User.nickname)
        data.put("message", message)
        data.put("chatAt", LocalDateTime.now().toString())

        stompClient.send("/pub/chat.${roomNum}", data.toString()).subscribe()
    }

    fun disconnect(){
        stompClient.disconnect()
    }
}