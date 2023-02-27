package com.smilestone.smarket.edit

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.retrofit.ConnectService
import com.smilestone.smarket.STATUS_OK
import com.smilestone.smarket.data.User

class EditViewModel(application: Application) : AndroidViewModel(application) {
    data class editData(var title: String, var price: Long, var content: String)


    private val _liveData = MutableLiveData<editData>()
    private val _code = MutableLiveData<Int>()

    val code: LiveData<Int>
        get() = _code

    val liveData: LiveData<editData>
        get() = _liveData

    init{
        _liveData.value = editData("" ,0,"")
    }

    fun upload(category: String){
        ConnectService.upload(category = category, title=_liveData.value?.title?:"", content=_liveData.value?.content ?: "", price = _liveData.value?.price?: 0, code = _code)
    }

    fun change(productId: Long, view: Long, category: String){
        ConnectService.changeProduct(category = category, productId = productId, view = view ,sellerId = User.id!!, title=_liveData.value?.title?:"", content=_liveData.value?.content ?: "", price = _liveData.value?.price?: 0)
    }

    fun checkCode(): Int{
        val result = when(_code.value){
            -1, CODE_FAIL -> {
                Toast.makeText(getApplication(), "서버 오류", Toast.LENGTH_SHORT).show()
                -1
            }
            STATUS_OK ->{
                1
            }
            else -> {
                Toast.makeText(getApplication(), "업로드 오류", Toast.LENGTH_SHORT).show()
                -1
            }
        }
        return result
    }
}