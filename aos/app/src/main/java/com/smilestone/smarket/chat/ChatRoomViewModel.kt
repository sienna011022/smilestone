package com.smilestone.smarket.chat

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.data.User
import com.smilestone.smarket.dto.Chat
import com.smilestone.smarket.stomp.StompService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import java.time.LocalDateTime

class ChatRoomViewModel(application: Application) : AndroidViewModel(application) {

    private val _list = ArrayList<Chat>()
    private val _chatList = MutableLiveData<ArrayList<Chat>>()
    private val _message = MutableLiveData<String>()
    private val _response = MutableLiveData<Int>()


    var roomNum : String


    val response: LiveData<Int>
        get() = _response

    val message : LiveData<String>
        get() = _message

    val chatList: LiveData<ArrayList<Chat>>
        get() = _chatList

    init{
        _chatList.value = ArrayList<Chat>()
        roomNum = ""
        _response.value = 0
    }


    fun startChat(room: String){
        StompService.runStomp(room, _chatList, _list)
    }

    fun sendMessage(){
        if(!_message.value.isNullOrBlank()){
            Log.d("테스트 스톰프2", _chatList.value.toString())
            StompService.sendMessage(_message.value!!)
        }
    }

    fun exit(){
        StompService.sendMessage("${User.nickname}님이 퇴장하셨습니다.t529tZ")
    }

    fun setMessage(message:String){
        _message.value = message
    }

    fun disconnect(){
        StompService.disconnect()
    }
}