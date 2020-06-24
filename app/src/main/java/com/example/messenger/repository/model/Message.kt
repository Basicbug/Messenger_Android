/*
 * Message.kt 2020. 5. 1
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model

import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.messenger.MessengerApp
import com.example.messenger.R
import com.example.messenger.push.PushItem
import com.example.messenger.repository.model.convertor.MessageTypeConverters
import com.example.messenger.type.MessageType
import com.google.gson.annotations.SerializedName

/**
 * @author MyeongKi
 */

@Entity(tableName = "messages", primaryKeys = ["room_id", "message_time"])
@TypeConverters(MessageTypeConverters::class)
data class Message(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("message")
    var message: String = "",
    @SerializedName("rooId")
    var roomId: String = "",
    @SerializedName("senderUid")
    var senderUid: String = "",
    @SerializedName("timestamp")
    var timestamp: String = "",
    @SerializedName("messageType")
    var messageType: MessageType = MessageType.MESSAGE
) : PushItem {
    override fun getTitle(): String {
        return senderUid
    }

    override fun getContent(): String {
        return when (messageType) {
            MessageType.MESSAGE -> {
                message
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