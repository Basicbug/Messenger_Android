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
        //타입을 먼저 확인하고 이에 맞튼 json 파싱이 이루어줘야 할 듯?
        when (remoteMessage.data["type"]) {
            "message" -> {
                //data에서 파싱 과정후 노티피케이션 호출...
                val testMessage = Message(
                    "sender", "receiver"
                    , MessageType.MESSAGE, "안녕", ""
                )
                //파싱해서 만들었다고 가정
                PushNotification(this, testMessage).show()
            }
            "system" -> {

            }
            else -> {

            }
        }

    }


}

// 푸시로직에서는 푸시만 진행하자...
//TODO 푸시 타입을 확인하고 각 상황에 맞는 이벤트 발생 처리는 각 이벤트를 구독한 usecase에서 하자