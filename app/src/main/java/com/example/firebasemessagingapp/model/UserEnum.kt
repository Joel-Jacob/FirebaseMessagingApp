package com.example.firebasemessagingapp.model

import com.example.firebasemessagingapp.R

enum class UserEnum(val user:Int) {
    Joel(R.string.Joel),
    Dalo(R.string.Dalo);

    fun next():UserEnum{
        if(this.name == "Joel")
            return Dalo
        else
            return Joel
    }
}