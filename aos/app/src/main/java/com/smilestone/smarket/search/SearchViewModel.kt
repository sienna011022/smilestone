package com.smilestone.smarket.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val _keyword = MutableLiveData<String>("");
    val keyword: LiveData<String>
        get() = _keyword

    fun setKeyword(keyword: String){
        _keyword.value = keyword
    }
}