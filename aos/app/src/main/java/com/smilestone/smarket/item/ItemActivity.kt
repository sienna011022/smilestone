package com.smilestone.smarket.item

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smilestone.smarket.PRODUCT_ID
import com.smilestone.smarket.chat.ChatRoomActivity
import com.smilestone.smarket.data.User
import com.smilestone.smarket.databinding.ActivityItemBinding
import com.smilestone.smarket.edit.EditActivity
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class ItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding
    private lateinit var model: ItemViewModel
    private val priceFormat = DecimalFormat("#,###")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)


        model = ViewModelProvider(this)[ItemViewModel::class.java]
        val productId = intent.getLongExtra(PRODUCT_ID, 0)
        model.item(productId)


        model.code.observe(this, Observer{
            model.product.observe(this, Observer{
                model.checkCode()
                binding.title.text = model.item.value?.title
                val time = LocalDateTime.parse(model.item.value?.time)
                binding.time.text = convertLocalDateTimeToTime(time)
                binding.content.text = model.item.value?.content
                binding.textPrice.text = priceFormat.format(model.item.value?.price) + "원"
                binding.category.text = model.item.value?.category
                model.getUser()

                if(model.product.value?.sellerId == User.id){
                    binding.btnChange.visibility = View.VISIBLE
                    binding.btnDelete.visibility = View.VISIBLE
                }
            })
        })

        model.nickname.observe(this, Observer {
            binding.userNickname.text = model.nickname.value.toString()
        })



        binding.btnDelete.setOnClickListener {
            model.delete()
            Toast.makeText(this, "삭제완료", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnChange.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("title", model.item.value?.title)
            intent.putExtra("content", model.item.value?.content)
            intent.putExtra("price", model.item.value?.price.toString())
            intent.putExtra("isEdit", 1)
            intent.putExtra("productId", model.product.value?.productId)
            intent.putExtra("view", model.product.value?.view)
            startActivity(intent)
            finish()
        }

        binding.btnChat.setOnClickListener {
            val intent = Intent(this, ChatRoomActivity::class.java)
            intent.putExtra("roomTitle", model.product.value?.title)
            intent.putExtra("productId", model.product.value?.productId)
            startActivity(intent)
        }

    }

    fun convertLocalDateTimeToTime(localDateTime: LocalDateTime): String? {
        val now: LocalDateTime = LocalDateTime.now()
        var diffTime: Long = localDateTime.until(now, ChronoUnit.SECONDS) - 60*60*9 // now보다 이후면 +, 전이면 -
        Log.d("테스트 시간", diffTime.toString())
        val msg: String? = null
        if (diffTime < 60) {
            return diffTime.toString() + "초전"
        }
        diffTime = diffTime / 60
        if (diffTime < 60) {
            return diffTime.toString() + "분 전"
        }
        diffTime = diffTime / 60
        if (diffTime < 24) {
            return diffTime.toString() + "시간 전"
        }
        diffTime = diffTime / 24
        if (diffTime < 28) {
            return diffTime.toString() + "일 전"
        }
        diffTime = diffTime / 28
        if (diffTime < 12) {
            return diffTime.toString() + "개월 전"
        }
        return  diffTime.toString()
    }

}