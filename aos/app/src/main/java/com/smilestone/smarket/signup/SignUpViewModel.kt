package com.smilestone.smarket.signup

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.REQUSET_ERROR
import com.smilestone.smarket.REQUSET_OK
import com.smilestone.smarket.STATUS_OK
import com.smilestone.smarket.retrofit.ConnectService

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    data class userData(var id: String, var pw: String, var nickname: String)

    private val _signUpData = MutableLiveData<userData>()
    private val _code = MutableLiveData<Int>()
    private val _result = MutableLiveData<Boolean>()

    val result : LiveData<Boolean>
        get() = _result

    val signUpData: LiveData<userData>
        get() = _signUpData

    val code : LiveData<Int>
        get() = _code

    init{
        _signUpData.value = userData("","","")
    }

    fun signUp(){
        if(!checkData())
            return
        ConnectService.signUp(_signUpData.value?.id.toString(),_signUpData.value?.pw.toString(), _signUpData.value?.nickname.toString(), _code)
        //Log.d("확인용", result.toString())

    }

    fun checkRedundancy(){
        ConnectService.checkRedundancy(_signUpData.value?.id ?: "", _code, _result)
    }

    fun checkCode(): Int{
        Log.d("테스트", _code.value.toString())
        val result = when(_code.value){
            -1, CODE_FAIL -> {
                Toast.makeText(getApplication(), "서버 오류", Toast.LENGTH_SHORT).show()
                -1
            }
            REQUSET_OK->1
            STATUS_OK-> 2
            REQUSET_ERROR->{
                Toast.makeText(getApplication(), "이미 아이디가 존재합니다.", Toast.LENGTH_SHORT).show()
                -1
            }
            else -> {
                Toast.makeText(getApplication(), "회원가입 오류", Toast.LENGTH_SHORT).show()
                -1
            }
        }
        return result
    }

    private fun checkData(): Boolean{
        if(_signUpData.value?.id?.isEmpty() == true || _signUpData.value?.pw?.isEmpty() == true ||
             _signUpData.value?.nickname?.isEmpty() == true ){
            Toast.makeText(getApplication(), "회원가입 양식을 모두 채워주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}