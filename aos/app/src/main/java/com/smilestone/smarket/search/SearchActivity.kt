package com.smilestone.smarket.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.smilestone.smarket.home.HomeActivity
import com.smilestone.smarket.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var model: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[SearchViewModel::class.java]
        binding.btnSearch.setOnClickListener {
            HomeActivity.keyword = model.keyword.value.toString()
            HomeActivity.isSearch = true
            finish()
        }

        binding.contentSearch.doAfterTextChanged {
            model.setKeyword(binding.contentSearch.text.toString())
        }
    }
}