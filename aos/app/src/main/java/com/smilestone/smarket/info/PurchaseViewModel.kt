package com.smilestone.smarket.info

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilestone.smarket.CODE_FAIL
import com.smilestone.smarket.STATUS_OK
import com.smilestone.smarket.dto.Product
import com.smilestone.smarket.retrofit.ConnectService

class PurchaseViewModel(application: Application) : AndroidViewModel(application) {
    data class PostData(val id: Long, val title: String, val time: String, val price: String)

    private val list = ArrayList<PostData>()
    private val _posts = MutableLiveData<ArrayList<PostData>>()
    private val _code = MutableLiveData<Int>()
    private val _post = MutableLiveData<ArrayList<Product>>()

    val post : LiveData<ArrayList<Product>>
        get() = _post

    val posts : LiveData<ArrayList<PostData>>
        get() = _posts

    val code : LiveData<Int>
        get() = _code

    init{
        _posts.value = list
    }


    fun getPurchaseList(){
        ConnectService.getPurchaseList(_code, _post)
    }

    fun checkCode(){
        when(_code.value){
            -1, CODE_FAIL -> {
                Toast.makeText(getApplication(), "서버 오류", Toast.LENGTH_SHORT).show()
            }
            STATUS_OK ->{
                list.clear()
                for(i in 0 until post.value?.size!!){
                    val data = post.value?.get(i)
                    list.add(PostData(data?.productId ?: 0, data?.title ?: "", data?.localDateTime ?: "", data?.price.toString() ))
                }
                _posts.value = list
            }
            else -> {
                Toast.makeText(getApplication(), "구매 리스트를 불러올 수 없습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}