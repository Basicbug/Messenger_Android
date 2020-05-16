/*
 * FcmService.kt 2020. 4. 26
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.push.fcm

import com.example.messenger.push.notification.PushNotification
import com.example.messenger.repository.model.Message
import com.example.messenger.type.MessageType
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * @author MyeongKi
 */

class FcmService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {


    }


}

// 푸시로직에서는 푸시만 진행하자...
//푸시 성공시 로컬에 메시지를 저장하는 것은 별로 좋아보이지 않는다...