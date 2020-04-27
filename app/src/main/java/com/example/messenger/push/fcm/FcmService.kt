/*
 * FcmService.kt 2020. 4. 26
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.push.fcm

import android.content.Intent
import android.os.Bundle
import com.example.messenger.constants.FcmConstants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * @author MyeongKi
 */

class FcmService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        startIntentService(remoteMessage.data)
    }

    private fun startIntentService(data: Map<String, String>) {
        val bundle = Bundle()
        val keySet: Set<String> = data.keys
        for (key in keySet) {
            bundle.putString(key, data[key])
        }
        val intent: Intent = Intent()
        intent.putExtra(FcmConstants.BUNDLE, bundle)


    }
}