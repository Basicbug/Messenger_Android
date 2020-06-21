/*
 * Message.kt 2020. 5. 1
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model

import androidx.room.ColumnInfo
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
    @SerializedName("roomId")
    @ColumnInfo(name = "room_id")
    var roomId: String = "",
    @SerializedName("senderUid")
    var senderName: String = "",
    @SerializedName("receiver_name")
    var receiverName: String = "",
    @SerializedName("message_type")
    var messageType: MessageType = MessageType.MESSAGE,
    @SerializedName("message")
    var messageContent: String = "",
    @SerializedName("message_time")
    @ColumnInfo(name = "message_time")
    var messageTime: String = ""
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