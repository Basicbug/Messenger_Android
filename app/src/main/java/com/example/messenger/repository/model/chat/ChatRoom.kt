/*
 * ChatRoom.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model.chat

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.messenger.repository.model.convertor.StringListTypeConverter
import com.google.gson.annotations.SerializedName

/**
 * @author MyeongKi
 */
@Entity
@TypeConverters(StringListTypeConverter::class)
data class ChatRoom(
    @PrimaryKey
    @SerializedName("roomId")
    var roomId: String = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("participants")
    var participants: List<String> = emptyList(),
    @SerializedName("lastMessageId")
    var lastMessageId: String = ""
)