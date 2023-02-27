package com.smilestone.smarket.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilestone.smarket.databinding.ActivityPurchaseListBinding

class PurchaseListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPurchaseListBinding
    private lateinit var model: PurchaseViewModel
    private lateinit var purchaseListAdapter : PurchaseListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[PurchaseViewModel::class.java]

        purchaseListAdapter = PurchaseListAdapter(model, this)

        model.posts.observe(this){
            purchaseListAdapter.notifyDataSetChanged()
        }

        binding.purchaseList.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = purchaseListAdapter
        }

        model.getPurchaseList()

        model.code.observe(this, Observer {
            model.post.observe(this, Observer {
                model.checkCode()
            })
        })

    }
}