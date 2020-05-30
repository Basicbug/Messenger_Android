/*
 * ChatRoomViewHolder.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.chat.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.databinding.ItemChatRoomBinding
import com.example.messenger.repository.model.chat.ChatRoom

/**
 * @author MyeongKi
 */
class ChatRoomViewHolder(
    private val binding: ItemChatRoomBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(chatRoom: ChatRoom) {
        binding.viewModel = ChatRoomItemViewModel(chatRoom)
    }
}