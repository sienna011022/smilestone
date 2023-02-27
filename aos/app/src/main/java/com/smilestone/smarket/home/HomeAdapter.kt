package com.smilestone.smarket.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.smilestone.smarket.PRODUCT_ID
import com.smilestone.smarket.R
import com.smilestone.smarket.item.ItemActivity
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class HomeAdapter(private val model: HomeViewModel, private val context: Context): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private val priceFormat = DecimalFormat("#,###")
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val userImage: ImageView = view.findViewById<ImageView>(R.id.user_image)
        val title: TextView = view.findViewById<TextView>(R.id.title)
        val time: TextView = view.findViewById<TextView>(R.id.time)
        val price: TextView = view.findViewById<TextView>(R.id.price)

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val intent = Intent(context, ItemActivity::class.java)
            intent.putExtra(PRODUCT_ID, model.posts.value?.get(adapterPosition)?.id)
            startActivity(context, intent, null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text =model.posts.value?.get(position)?.title
        val time = LocalDateTime.parse(model.posts.value?.get(position)?.time)
        holder.time.text = convertLocalDateTimeToTime(time)
        holder.price.text = priceFormat.format(model.posts.value?.get(position)?.price?.toLong()) + "원"
    }

    override fun getItemCount(): Int = model.posts.value?.size ?: 0
}

fun convertLocalDateTimeToTime(localDateTime: LocalDateTime): String? {
    val now: LocalDateTime = LocalDateTime.now()
    var diffTime: Long = localDateTime.until(now, ChronoUnit.SECONDS)- 60*60*9 // now보다 이후면 +, 전이면 -
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