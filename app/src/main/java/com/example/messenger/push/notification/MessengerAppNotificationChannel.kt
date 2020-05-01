/*
 * MessengerAppNotificationChannel.kt 2020. 4. 29
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.push.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.messenger.MessengerApp
import com.example.messenger.R

/**
 * @author MyeongKi
 */

enum class MessengerAppNotificationChannel(val id: String, private val nameId: Int, private val channelImportance: Int) {
    MESSAGE("message_channel", R.string.channel_push_message, NotificationManager.IMPORTANCE_HIGH);

    fun createNotificationChannel(notificationManager: NotificationManager) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = MessengerApp.applicationContext().getString(nameId)
            val notificationChannel = NotificationChannel(id, name, channelImportance)
            notificationChannel.setShowBadge(false)

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}