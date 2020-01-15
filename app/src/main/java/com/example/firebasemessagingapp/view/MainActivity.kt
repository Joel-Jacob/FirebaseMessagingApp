package com.example.firebasemessagingapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.firebasemessagingapp.R
import com.example.firebasemessagingapp.adapter.MessageAdapter
import com.example.firebasemessagingapp.model.Messages
import com.example.firebasemessagingapp.model.UserEnum
import com.example.firebasemessagingapp.util.Util
import com.example.firebasemessagingapp.viewmodel.RealViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var userEnum: UserEnum
    private lateinit var viewModel: RealViewModel
    private lateinit var messageObserver: Observer<Messages>
    private val messages  = arrayListOf<Messages>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(RealViewModel::class.java)

        messageObserver = Observer<Messages> { message ->
            messages += message
            setRV(messages)
        }

        userEnum = UserEnum.Joel
        displayUser()

        profile_image.setOnClickListener {
            changeUser()
        }

        send_button.setOnClickListener {
            sendMessage()
        }

        viewModel.getLiveData().observe(this, messageObserver)

    }

    fun setRV(message: List<Messages>) {
        //Util.logDebug(message.messageContent + " " + message.messageDate)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = MessageAdapter(message)
    }

    fun sendMessage() {
        messages.clear()
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        var currentDateString = "" + currentDate
        val tempString = currentDateString.split( " ")

        //Util.logDebug(tempString[0]+ ", "+tempString[1])
        var temp = Messages(userEnum.name, "message", tempString[1].dropLast(3), message_et.text.toString())
        message_et.setText("")
        viewModel.sendMessage(temp)
        //setRV(temp)
    }

    fun changeUser() {
        userEnum = userEnum.next()
        Util.logDebug(userEnum.name)
        displayUser()
    }

    fun displayUser() {
        user_tv.text = userEnum.name

        Glide.with(this)
            .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
            .load(resources.getString(userEnum.user))
            .into(profile_image)

        //for Glide:
        //resources.getString(userEnum.user)
    }
}
