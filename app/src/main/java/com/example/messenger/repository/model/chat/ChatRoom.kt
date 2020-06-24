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
import com.example.messenger.repository.model.convertor.ListStringTypeConverter
import com.google.gson.annotations.SerializedName

/**
 * @author MyeongKi
 */
@Entity
@TypeConverters(ListStringTypeConverter::class)
data class ChatRoom(
    @PrimaryKey
    @SerializedName("roomId")
    var roomId: String = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("participants")
    var participants: List<String> = emptyList()
){
    @SerializedName("lastMessage")
    @Ignore
    var lastMessage: Message? = null
}
//TODO 참가자 마지막 메시지에 대한 관계형 정의가 필요할 듯