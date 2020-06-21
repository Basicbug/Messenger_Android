/*
 * ChatRoom.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model.chat

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.messenger.repository.model.Message
import com.example.messenger.repository.model.convertor.DateTypeConverter
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author MyeongKi
 */
@Entity
data class ChatRoom(
    @PrimaryKey
    @SerializedName("roomId")
    var roomId: String = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("lastMessage")
    var lastMessage: Message?,
    @SerializedName("participants")
    var participants: List<String>?
)