package com.smilestone.smarket.search

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.STATUS_OK
import com.smilestone.smarket.dto.Product
import com.smilestone.smarket.home.HomeViewModel
import com.smilestone.smarket.retrofit.ConnectService

class CategoryListViewModel(application: Application) : AndroidViewModel(application) {

    private val _product = MutableLiveData<ArrayList<Product>>()
    private val _code = MutableLiveData<Int>()


    val product : LiveData<ArrayList<Product>>
        get() = _product

    val code: LiveData<Int>
        get() = _code

    init{
        _product.value = ArrayList<Product>()
    }

    fun getProduct(category: String?){
        ConnectService.getProduct(category, _code, _product)
    }

    fun checkCode(){
        when(_code.value){
            -1, CODE_FAIL -> {
                Toast.makeText(getApplication(), "서버 오류", Toast.LENGTH_SHORT).show()
            }
            STATUS_OK ->{

            }
            else -> {
                Toast.makeText(getApplication(), "게시글 불러오기 오류", Toast.LENGTH_SHORT).show()
            }
        }
    }
}