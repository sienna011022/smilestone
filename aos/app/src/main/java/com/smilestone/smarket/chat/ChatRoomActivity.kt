package com.smilestone.smarket.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilestone.smarket.R
import com.smilestone.smarket.data.User
import com.smilestone.smarket.databinding.ActivityChatRoomBinding
import com.smilestone.smarket.dto.Chat
import java.util.*
import kotlin.collections.ArrayList

class ChatRoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatRoomBinding
    private lateinit var model: ChatRoomViewModel
    private lateinit var chatAdapter: ChatRoomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nickname.text = intent.getStringExtra("roomTitle")
        val roomNum = intent.getLongExtra("productId", 0)
        val room = roomNum.toString()
        Log.d("테스트 채팅", "룸 ${room}")

        model = ViewModelProvider(this)[ChatRoomViewModel::class.java]
        model.startChat(room)

        chatAdapter = ChatRoomAdapter(model, this)

        binding.editMessage.doAfterTextChanged {
            model.setMessage(binding.editMessage.text.toString())
        }


        binding.btnSend.setOnClickListener {
            model.sendMessage()
            binding.editMessage.setText("")
        }


        model.chatList.observe(this, Observer {
            Log.d("테스트 스톰프20", model.chatList.value.toString())
            chatAdapter.notifyDataSetChanged()
        })

        binding.chatList.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = chatAdapter
        }
    }

    override fun onStop() {
        super.onStop()
        model.exit()
        model.disconnect()
    }
}