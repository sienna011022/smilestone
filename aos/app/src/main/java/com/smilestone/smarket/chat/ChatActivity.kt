package com.smilestone.smarket.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import com.smilestone.smarket.databinding.ActivityChatBinding
import com.smilestone.smarket.edit.EditActivity
import com.smilestone.smarket.home.HomeActivity
import com.smilestone.smarket.info.InfoActivity
import com.smilestone.smarket.item.ItemActivity
import com.smilestone.smarket.search.SearchActivity

class ChatActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener(this)
        binding.btnInfo.setOnClickListener(this)
        binding.btnChat.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Log.d("intent", v.toString())
        val intent = when(v?.id){
            binding.btnHome.id-> Intent(applicationContext, HomeActivity::class.java)
            binding.btnChat.id -> Intent(applicationContext, ChatActivity::class.java)
            binding.btnInfo.id -> Intent(applicationContext, InfoActivity::class.java)
            else -> return
        }
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }
}