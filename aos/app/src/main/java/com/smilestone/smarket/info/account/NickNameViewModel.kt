package com.smilestone.smarket.info.account

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.STATUS_OK
import com.smilestone.smarket.home.HomeViewModel
import com.smilestone.smarket.retrofit.ConnectService

class NickNameViewModel(application: Application) : AndroidViewModel(application) {
    private val _code = MutableLiveData<Int>()
    private val _nickname = MutableLiveData<String>()

    val code: LiveData<Int>
        get() = _code

    val nickname : LiveData<String>
        get() = _nickname

    init{
        _nickname.value = ""
    }

    fun changeNickName(){
        ConnectService.changeNickName(_nickname.value!!,_code)
    }

    fun setNickName(nickname: String){
        _nickname.value = nickname
    }

    fun checkCode(): Int{
        Log.d("테스트", _code.value.toString())
        return when(_code.value){
            -1, CODE_FAIL -> {
                Toast.makeText(getApplication(), "서버 오류", Toast.LENGTH_SHORT).show()
                -1
            }
            STATUS_OK ->{
                1
            }
            else -> {
                Toast.makeText(getApplication(), "닉네임 변경 오류", Toast.LENGTH_SHORT).show()
                -1
            }
        }
    }
}