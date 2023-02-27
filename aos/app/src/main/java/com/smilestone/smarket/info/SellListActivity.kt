package com.smilestone.smarket.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilestone.smarket.databinding.ActivitySellListBinding

class SellListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySellListBinding
    private lateinit var model: SellListViewModel
    private lateinit var sellListAdapter: SellListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[SellListViewModel::class.java]

        sellListAdapter = SellListAdapter(model, this)

        model.posts.observe(this){
            sellListAdapter.notifyDataSetChanged()
        }

        binding.sellList.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = sellListAdapter
        }

        model.getSellList()

        model.code.observe(this, Observer {
            model.post.observe(this, Observer {
                model.checkCode()
            })
        })

    }
}