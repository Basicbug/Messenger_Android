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
import com.google.gson.annotations.SerializedName

/**
 * @author MyeongKi
 */
@Entity
data class ChatRoom(
    @PrimaryKey
    @SerializedName("roomId")
    var roomId: String = "",
    @SerializedName("name")
    var name: String? = ""

){
    @SerializedName("lastMessage")
    @Ignore
    var lastMessage: Message? = null
    @SerializedName("participants")
    @Ignore
    var participants: List<String>? = null
}
//TODO 참가자 마지막 메시지에 대한 관계형 정의가 필요할 듯