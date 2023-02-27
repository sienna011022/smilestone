package com.smilestone.smarket.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.notification.Condition.isValidId
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smilestone.smarket.databinding.ActivitySignupBinding
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var model: SignUpViewModel
    private var isRedundant: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this)[SignUpViewModel::class.java]


        binding.editId.doAfterTextChanged {
            model.signUpData.value?.id = binding.editId.text.toString()
            isValidId()
            isRedundant = true
        }

        binding.editPw.doAfterTextChanged {
            model.signUpData.value?.pw = binding.editPw.text.toString()
            isValidPw()
        }
        binding.editPwCheck.doAfterTextChanged {
            checkSame()
        }

        binding.btnRedundancy.setOnClickListener {
            model.checkRedundancy()
        }





        binding.editNickname.doAfterTextChanged {
            model.signUpData.value?.nickname = binding.editNickname.text.toString()
        }
        binding.btnSingup.setOnClickListener {
            if(isValidId() && isValidPw() && checkSame() && !binding.editNickname.text.isNullOrEmpty() && !isRedundant)
                model.signUp()
            else
                Toast.makeText(this, "회원가입 양식을 확인해주세요", Toast.LENGTH_SHORT).show()
        }

        model.code.observe(this, Observer {
            val result = model.checkCode()
            if(result==1){
                Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT).show()
                finish()
            }
        })

        model.result.observe(this, Observer {
            if(model.result.value == true){
                Toast.makeText(this, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show()
            }
            else{
                isRedundant = false
                Toast.makeText(this, "유효한 아이디", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun isValidId():Boolean{
        val idPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{5,12}$"
        val pattern = Pattern.compile(idPattern)
        val matcher = pattern.matcher(binding.editId.text.toString())

        binding.checkId.visibility = if(!matcher.find()) View.VISIBLE else View.INVISIBLE

        return !binding.checkId.isVisible
    }

    private fun isValidPw() : Boolean {
        checkSame()
        val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,15}$"
        val pattern = Pattern.compile(pwPattern)
        val matcher = pattern.matcher(binding.editPw.text.toString())

        binding.checkPwReg.visibility = if(!matcher.find()) View.VISIBLE else View.INVISIBLE

        return !binding.checkPwReg.isVisible
    }

    private fun checkSame() :Boolean{
        binding.checkPw.visibility = if(!binding.editPw.text.toString().equals(binding.editPwCheck.text.toString())) View.VISIBLE else View.INVISIBLE
        return !binding.checkPw.isVisible
    }
}