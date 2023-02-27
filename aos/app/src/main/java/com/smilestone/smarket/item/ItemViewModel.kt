package com.smilestone.smarket.item

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
import com.smilestone.smarket.dto.Product

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    data class itemData(var title: String, var time: String, var content: String, var view: Long, var price: Long, var category: String)

    private val _item = MutableLiveData<itemData>()
    private val _product = MutableLiveData<Product>()
    private val _code = MutableLiveData<Int>()
    private val _response = MutableLiveData<Int>()
    private val _nickname = MutableLiveData<String>()


    val nickname : LiveData<String>
        get() = _nickname

    val response: LiveData<Int>
        get() = _response

    val code: LiveData<Int>
        get() = _code
    val item : LiveData<itemData>
        get() = _item

    val product: LiveData<Product>
        get() = _product

    init{
        _item.value = itemData("","","",0, 0, "기타")
    }

    fun item(productId: Long){
        ConnectService.item(productId, _code, _product)
    }

    fun delete(){
        Log.d("테스트 삭제", _product.value?.productId.toString())
        ConnectService.deleteProduct(_product.value?.productId ?:0 , _response)
    }

    fun getUser(){
        ConnectService.getSellerUser(_product.value?.sellerId?:0, _nickname)
    }

    fun responseReset(){
        _response.value =0
    }

    fun checkResponse(): Int{
        Log.d("테스트", _response.value.toString())
        val result = when(_response.value){
            -1, CODE_FAIL -> {
                Toast.makeText(getApplication(), "서버 오류", Toast.LENGTH_SHORT).show()
                -1
            }
            STATUS_OK -> 1
            else -> {
                Toast.makeText(getApplication(), "삭제 실패", Toast.LENGTH_SHORT).show()
                -1
            }
        }
        return result
    }

    fun checkCode(){
        _item.value?.title = _product.value?.title ?: ""
        _item.value?.time = _product.value?.localDateTime ?: ""
        _item.value?.content = _product.value?.content ?: ""
        _item.value?.view = _product.value?.view ?: 0
        _item.value?.price = _product.value?.price ?: 0
        _item.value?.category = _product.value?.category ?: "기타"
    }
}