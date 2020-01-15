package com.example.firebasemessagingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.firebasemessagingapp.model.Messages
import com.example.firebasemessagingapp.util.Constants
import com.example.firebasemessagingapp.util.Util
import com.google.firebase.database.*

class RealViewModel(application: Application) : AndroidViewModel(application) {
    private var messageReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference(Constants.DATABASE_URL)
    private var mutableLiveData = MutableLiveData<Messages>()

    init{
        messageReference.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Util.logError(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (currentData in p0.children) {
                    mutableLiveData.value = currentData.getValue(Messages::class.java)
                }
            }
        })
    }

    fun sendMessage(message:Messages){
        var childKey = messageReference.push().key

        if(childKey!=null)
            messageReference.child(childKey).setValue(message)
    }

    fun getLiveData():MutableLiveData<Messages>{
        return mutableLiveData
    }
}