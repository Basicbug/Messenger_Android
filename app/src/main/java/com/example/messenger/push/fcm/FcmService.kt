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
//푸시 성공시 로컬에 메시지를 저장하는 것은 별로 좋아보이지 않는다...