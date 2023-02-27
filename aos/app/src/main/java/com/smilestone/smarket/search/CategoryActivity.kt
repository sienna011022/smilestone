package com.smilestone.smarket.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.smilestone.smarket.R
import com.smilestone.smarket.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEtc.setOnClickListener(this)
        binding.btnComputer.setOnClickListener(this)
        binding.btnMath.setOnClickListener(this)
        binding.btnEnglish.setOnClickListener(this)
        binding.btnKorean.setOnClickListener(this)
        binding.btnScience.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val category = when(v?.id){
            binding.btnComputer.id -> "컴퓨터"
            binding.btnEnglish.id -> "영어"
            binding.btnEtc.id -> "기타"
            binding.btnMath.id -> "수학"
            binding.btnKorean.id -> "국어"
            binding.btnScience.id -> "과학"
            else -> return
        }
        val intent = Intent(this, CategoryListActivity::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }
}