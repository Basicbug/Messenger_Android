/*
 * Message.kt 2020. 5. 1
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model.chat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.messenger.app.MessengerApp
import com.example.messenger.R
import com.example.messenger.app.AppResources
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
    @ColumnInfo(name = "id")
    var id: String = "",
    @SerializedName("message")
    var message: String = "",
    @SerializedName("rooId")
    @ColumnInfo(name = "room_id")
    var roomId: String = "",
    @SerializedName("senderUid")
    var senderUid: String = "",
    @SerializedName("timestamp")
    @ColumnInfo(name = "message_time")
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
                AppResources.getStringResId(R.string.image_message_content)
            }
            else -> {
                ""
            }
        }
    }

}