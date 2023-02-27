package com.smilestone.smarket.chat

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat.setBackground
import androidx.recyclerview.widget.RecyclerView
import com.smilestone.smarket.PRODUCT_ID
import com.smilestone.smarket.R
import com.smilestone.smarket.data.User
import com.smilestone.smarket.home.HomeAdapter
import com.smilestone.smarket.home.HomeViewModel
import com.smilestone.smarket.item.ItemActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChatRoomAdapter(private val model: ChatRoomViewModel, private val context: Context): RecyclerView.Adapter<ChatRoomAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val inout: TextView = view.findViewById<TextView>(R.id.message_inout)
        val linear: LinearLayout = view.findViewById<LinearLayout>(R.id.linear)
        val nickname: TextView = view.findViewById<TextView>(R.id.message_nickname)
        val background: ImageView = view.findViewById<ImageView>(R.id.message_background)
        val message: TextView = view.findViewById<TextView>(R.id.message_content)
        val myTime: TextView = view.findViewById<TextView>(R.id.my_time)
        val partnerTime: TextView = view.findViewById<TextView>(R.id.partner_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = model.chatList.value?.get(position)
        if(message?.message?.length ?: 0 > 6 && message?.message?.substring(message.message.length-6).equals("t529tZ")){
            holder.inout.text = message?.message?.substring(0, message?.message?.length!! -6)
            holder.nickname.visibility = View.GONE
            holder.background.visibility = View.GONE
            holder.message.visibility = View.GONE
            holder.myTime.visibility = View.GONE
            holder.partnerTime.visibility = View.GONE
        }
        else{
            holder.inout.visibility = View.GONE
            val time = LocalDateTime.parse(message?.chatAt)
            if(!message?.sender.equals(User.nickname)){
                holder.nickname.text = message?.sender
                holder.nickname.visibility = View.VISIBLE
                holder.background.visibility = View.VISIBLE
                holder.message.visibility = View.VISIBLE
                holder.myTime.visibility = View.GONE
                holder.partnerTime.visibility = View.VISIBLE
                val icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.partner_chat)
                holder.background.setBackground(BitmapDrawable(icon))
                holder.partnerTime.text = time.format(DateTimeFormatter.ofPattern("a h:mm"))
                holder.myTime.text = ""
            }
            else{
                holder.nickname.visibility = View.GONE
                holder.background.setImageResource(R.drawable.my_chat)
                holder.linear.gravity = Gravity.END
                holder.background.visibility = View.VISIBLE
                holder.message.visibility = View.VISIBLE
                holder.myTime.visibility = View.VISIBLE
                holder.partnerTime.visibility = View.GONE
                val icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.my_chat)
                holder.background.setBackground(BitmapDrawable(icon))
                holder.myTime.text = time.format(DateTimeFormatter.ofPattern("a h:mm"))
                holder.partnerTime.text = ""
            }
            holder.message.text = message?.message
        }
    }

    override fun getItemCount(): Int = model.chatList.value?.size ?: 0
}