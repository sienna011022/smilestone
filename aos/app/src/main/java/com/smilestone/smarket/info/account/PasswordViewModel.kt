package com.smilestone.smarket.info.account

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.STATUS_OK
import com.smilestone.smarket.retrofit.ConnectService

class PasswordViewModel(application: Application) : AndroidViewModel(application) {
    private val _code = MutableLiveData<Int>()
    private val _password = MutableLiveData<String>()
    private val _newPassword = MutableLiveData<String>()

    val code: LiveData<Int>
        get() = _code

    val password : LiveData<String>
        get() = _password

    val newPassword : LiveData<String>
        get() = _newPassword

    init{
        _password.value = ""
        _newPassword.value = ""
    }

    fun changePassword(){
        ConnectService.changePassword(_password.value!!,_newPassword.value!!, _code)
    }

    fun setPassword(password: String){
        _password.value = password
    }

    fun setNewPassword(password: String){
        _newPassword.value = password
    }

    fun checkCode(): Int{
        Log.d("테스트 비밀번호!", _code.value.toString())
        return when(_code.value){
            -1, CODE_FAIL -> {
                Toast.makeText(getApplication(), "서버 오류", Toast.LENGTH_SHORT).show()
                -1
            }
            STATUS_OK, 1001 ->{
                1
            }
            else -> {
                Toast.makeText(getApplication(), "비밀번호 변경 오류", Toast.LENGTH_SHORT).show()
                -1
            }
        }
    }
}