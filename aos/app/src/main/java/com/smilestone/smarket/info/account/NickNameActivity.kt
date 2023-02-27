package com.smilestone.smarket.info.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smilestone.smarket.R
import com.smilestone.smarket.databinding.ActivityNickNameBinding

class NickNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNickNameBinding
    private lateinit var model: NickNameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNickNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[NickNameViewModel::class.java]

        binding.editNickname.doAfterTextChanged {
            model.setNickName(binding.editNickname.text.toString())
        }

        binding.btnChange.setOnClickListener {
            model.changeNickName()
        }

        model.code.observe(this, Observer {
            val result = model.checkCode()
            if(result==1){
                Toast.makeText(this, "닉네임 변경 완료", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }
}