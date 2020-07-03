/*
 * MessengerApp.kt 2020. 6. 29
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.app

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