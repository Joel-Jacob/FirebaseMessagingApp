package com.example.firebasemessagingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.firebasemessagingapp.R
import com.example.firebasemessagingapp.model.Messages
import com.example.firebasemessagingapp.util.Constants
import kotlinx.android.synthetic.main.messaging_item_1_layout.view.*

class MessageAdapter(val messages: List<Messages>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val USER_ONE = 0
    private val USER_TWO = 1
    private lateinit var context: Context

    override fun getItemViewType(position: Int): Int {
        if(messages[position].username == "Joel")
            return USER_ONE
        else return USER_TWO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context.applicationContext

        if(viewType == USER_ONE){
            var view = LayoutInflater.from(parent.context)
                .inflate(R.layout.messaging_item_1_layout, parent, false)
            return Message1ViewHolder(view)
        }
        else{
            var view = LayoutInflater.from(parent.context)
                .inflate(R.layout.messaging_item_2_layout, parent, false)
            return Message2ViewHolder(view)
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position)== 1){
            Message1ViewHolder(holder.itemView).setMessage(messages[position], context)
        }
        else{
            Message2ViewHolder(holder.itemView).setMessage(messages[position], context)
        }
    }

    class Message1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var content: TextView = itemView.item_content_tv
        var date: TextView = itemView.item_date_tv
        var profile: ImageView = itemView.item_profile_iv

        fun setMessage(message:Messages, context:Context){
            content.text = message.messageContent
            date.text = message.messageDate

            Glide.with(context)
                .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(Constants.DALO_IMAGE_URL)
                .into(profile)
        }
    }

    class Message2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var content: TextView = itemView.item_content_tv
        var date: TextView = itemView.item_date_tv
        var profile: ImageView = itemView.item_profile_iv

        fun setMessage(message:Messages, context:Context){
            content.text = message.messageContent
            date.text = message.messageDate

            Glide.with(context)
                .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(Constants.JOEL_IMAGE_URL)
                .into(profile)
        }
    }
}