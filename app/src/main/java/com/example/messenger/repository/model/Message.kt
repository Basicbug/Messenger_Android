/*
 * Message.kt 2020. 5. 1
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model

import androidx.room.Entity
import com.example.messenger.MessengerApp
import com.example.messenger.R
import com.example.messenger.push.PushItem
import com.example.messenger.type.MessageType
import com.google.gson.annotations.SerializedName

/**
 * @author MyeongKi
 */

@Entity(tableName = "messages", primaryKeys = ["room_id", "message_time"])
data class Message(
    @SerializedName("room_id")
    val roomID: Int,
    @SerializedName("sender_name")
    val senderName: String,
    @SerializedName("receiver_name")
    val receiverName: String,
    @SerializedName("message_type")
    val messageType: MessageType,
    @SerializedName("message_content")
    val messageContent: String,
    @SerializedName("message_time")
    val messageTime: String
) : PushItem {
    override fun getTitle(): String {
        return senderName
    }

    override fun getContent(): String {
        return when (messageType) {
            MessageType.MESSAGE -> {
                messageContent
            }
            MessageType.IMAGE -> {
                MessengerApp.applicationContext().resources.getString(R.string.image_message_content)
            }
            else -> {
                ""
            }
        }
    }

}