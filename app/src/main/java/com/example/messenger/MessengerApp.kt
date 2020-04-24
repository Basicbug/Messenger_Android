/*
 * MessengerApp.kt 2020. 4. 23
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger

import android.app.Application
import android.content.Context

/**
 * @author MyeongKi
 */

class MessengerApp : Application() {

    init {
        instance = this

    }


    companion object {
        private var instance: MessengerApp? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}