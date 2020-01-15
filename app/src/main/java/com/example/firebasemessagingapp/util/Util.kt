package com.example.firebasemessagingapp.util

import android.util.Log

object Util {
    fun logDebug(message: String) {
        Log.d("TAG_X", "Debug: " + message)
    }

    fun logError(message: String) {
        Log.d("TAG_X", "Error: " + message)
    }
}
