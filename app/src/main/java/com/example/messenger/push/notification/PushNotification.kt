/*
 * PushNotification.kt 2020. 4. 29
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.push.notification

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.messenger.R
import com.example.messenger.push.PushItem

/**
 * @author MyeongKi
 */

class PushNotification(context: Context, pushItem: PushItem) {
    private val notificationBuilder: NotificationCompat.Builder
    private val notificationManager: NotificationManager = context.getSystemService(
        Context
            .NOTIFICATION_SERVICE
    ) as NotificationManager

    init {
        MessengerAppNotificationChannel.MESSAGE.createNotificationChannel(notificationManager)
        notificationBuilder = NotificationCompat.Builder(context, MessengerAppNotificationChannel.MESSAGE.id)
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)//앱 이미지...
        notificationBuilder.setAutoCancel(true)
        notificationBuilder.setDefaults(Notification.DEFAULT_ALL)
        notificationBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        notificationBuilder.setContentTitle(pushItem.getTitle())
        notificationBuilder.setContentText(pushItem.getContent())
    }

    fun show() {
        val notificationId = (System.currentTimeMillis() % 10000).toInt()
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

}