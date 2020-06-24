/*
 * ChatRoomDiffCallback.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.chat.chatroomlist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.messenger.repository.model.chat.ChatRoom

/**
 * @author MyeongKi
 */
class ChatRoomDiffCallback : DiffUtil.ItemCallback<ChatRoom>() {
    override fun areItemsTheSame(oldItem: ChatRoom, newItem: ChatRoom): Boolean {
        return oldItem.roomId == newItem.roomId
    }

    override fun areContentsTheSame(oldItem: ChatRoom, newItem: ChatRoom): Boolean {
        return (oldItem.toString() == newItem.toString())
    }

}