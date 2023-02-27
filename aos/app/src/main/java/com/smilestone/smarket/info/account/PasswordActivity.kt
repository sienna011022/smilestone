package com.smilestone.smarket.info.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smilestone.smarket.R
import com.smilestone.smarket.databinding.ActivityPasswordBinding

class PasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasswordBinding
    private lateinit var model: PasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[PasswordViewModel::class.java]

        binding.editPassword.doAfterTextChanged {
            model.setPassword(binding.editPassword.text.toString())
        }

        binding.editNewPassword.doAfterTextChanged {
            model.setNewPassword(binding.editNewPassword.text.toString())
        }

        binding.btnChange.setOnClickListener {
            model.changePassword()
        }

        model.code.observe(this, Observer {
            val result = model.checkCode()
            if(result==1){
                Toast.makeText(this, "비밀번호 변경 완료", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }
}