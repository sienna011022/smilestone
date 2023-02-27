package com.smilestone.smarket.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilestone.smarket.R
import com.smilestone.smarket.databinding.ActivityCategoryListBinding

class CategoryListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryListBinding
    private lateinit var model: CategoryListViewModel
    private lateinit var categoryAdapter: CategoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getStringExtra("category")
        binding.category.text = category

        model = ViewModelProvider(this)[CategoryListViewModel::class.java]

        categoryAdapter = CategoryListAdapter(model, this)

        binding.itemList.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = categoryAdapter
        }

        model.code.observe(this, Observer {
            model.product.observe(this, Observer {
                categoryAdapter.notifyDataSetChanged()
            })
        })

        model.getProduct(category)

    }
}