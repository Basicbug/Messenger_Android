/*
 * Message.kt 2020. 5. 1
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model

import com.example.messenger.MessengerApp
import com.example.messenger.R
import com.example.messenger.push.PushItem
import com.example.messenger.type.MessageType

/**
 * @author MyeongKi
 */

data class Message(
    val senderName: String,
    val receiverName: String,
    val messageType: MessageType,
    val messageContents: String,
    val messageTime: String
) : PushItem {
    override fun getTitle(): String {
        return senderName
    }

    override fun getContent(): String {
        return when (messageType) {
            MessageType.MESSAGE -> {
                messageContents
            }
            MessageType.IMAGE -> {
                MessengerApp.applicationContext().resources.getString(R.string.image_message_contents)
            }
            else -> {
                ""
            }
        }
    }

}