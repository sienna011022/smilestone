package com.smilestone.smarket.info

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import com.smilestone.smarket.LOGIN_TOKEN
import com.smilestone.smarket.chat.ChatActivity
import com.smilestone.smarket.data.User
import com.smilestone.smarket.databinding.ActivityInfoBinding
import com.smilestone.smarket.home.HomeActivity
import com.smilestone.smarket.info.account.NickNameActivity
import com.smilestone.smarket.info.account.PasswordActivity
import com.smilestone.smarket.login.LoginActivity

class InfoActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener(this)
        binding.btnInfo.setOnClickListener(this)
        binding.btnChat.setOnClickListener(this)
        binding.layerPurchase.setOnClickListener(this)
        binding.layerSell.setOnClickListener (this)
        binding.layerLogout.setOnClickListener(this)
        binding.layerChangeNickname.setOnClickListener(this)
        binding.layerChangePassword.setOnClickListener(this)


        binding.layerLogout.setOnClickListener {
            logout()
        }

    }

    override fun onResume() {
        super.onResume()
        binding.nickname.text = User.nickname
    }

    override fun onClick(v: View?) {
        Log.d("intent", v.toString())
        val intent = when(v?.id){
            binding.btnHome.id-> Intent(applicationContext, HomeActivity::class.java)
            binding.btnChat.id -> Intent(applicationContext, ChatActivity::class.java)
            binding.btnInfo.id -> Intent(applicationContext, InfoActivity::class.java)
            binding.layerPurchase.id -> Intent(applicationContext, PurchaseListActivity::class.java)
            binding.layerSell.id -> Intent(applicationContext, SellListActivity::class.java)
            binding.layerChangeNickname.id -> Intent(applicationContext, NickNameActivity::class.java)
            binding.layerChangePassword.id -> Intent(applicationContext, PasswordActivity::class.java)
            else -> return
        }
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

    private fun logout(){
        with(getSharedPreferences(LOGIN_TOKEN, MODE_PRIVATE).edit()){
            clear()
            commit()
        }
        val intent= Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}