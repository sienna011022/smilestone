package com.smilestone.smarket.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smilestone.smarket.PRODUCT_ID
import com.smilestone.smarket.R
import com.smilestone.smarket.home.HomeActivity
import com.smilestone.smarket.databinding.ActivityEditBinding
import com.smilestone.smarket.item.ItemActivity
import java.text.DecimalFormat

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private lateinit var model: EditViewModel
    private val priceFormat = DecimalFormat("#,###")
    private var result:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[EditViewModel::class.java]


        binding.categoryList.adapter = ArrayAdapter.createFromResource(
            this, R.array.category_item, android.R.layout.simple_list_item_1
        )

        binding.btnExit.setOnClickListener{
            finish()
        }


        model.code.observe(this, Observer {
            val result = model.checkCode()
            if(result == 1){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

        binding.editTitle.doAfterTextChanged {
            model.liveData.value?.title = binding.editTitle.text.toString()

        }
        binding.editPrice.doAfterTextChanged {
            if(result.equals(binding.editPrice.text.toString())){
                return@doAfterTextChanged
            }
            model.liveData.value?.price = binding.editPrice.text.toString().replace(",","").toLong()
            result = priceFormat.format(model.liveData.value?.price)
            binding.editPrice.setText(result)
            binding.editPrice.setSelection(result.length)

        }
        binding.editContent.doAfterTextChanged {
            model.liveData.value?.content = binding.editContent.text.toString()

        }

        binding.editTitle.setText(intent.getStringExtra("title"))
        binding.editPrice.setText(intent.getStringExtra("price"))
        binding.editContent.setText(intent.getStringExtra("content"))
        val productId = intent.getLongExtra("productId", 0)
        val isEdit = intent.getIntExtra("isEdit", 0)
        val view = intent.getLongExtra("view", 0)

        binding.btnEdit.setOnClickListener {
            if(isEdit==0)
                model.upload(binding.categoryList.selectedItem.toString())
            else{
                model.change(productId, view, binding.categoryList.selectedItem.toString())
                val intent = Intent(this, ItemActivity::class.java)
                intent.putExtra(PRODUCT_ID, productId)
                startActivity(intent)
                finish()
            }
        }

    }

}