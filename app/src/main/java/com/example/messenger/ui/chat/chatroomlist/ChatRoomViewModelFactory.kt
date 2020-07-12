/*
 * ChatRoomViewModelFactory.kt 2020. 5. 30
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.chat.chatroomlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.messenger.repository.chat.ChatRoomRepositoryImpl
import com.example.messenger.repository.chat.MessageRepositoryImpl

/**
 * @author MyeongKi
 */
class ChatRoomViewModelFactory(
    private val chatRoomRepository: ChatRoomRepositoryImpl,
    private val messageRepository: MessageRepositoryImpl
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatRoomListViewModel(chatRoomRepository, messageRepository) as T
    }
}