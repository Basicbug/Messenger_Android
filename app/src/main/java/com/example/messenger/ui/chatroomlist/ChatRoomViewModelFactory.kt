/*
 * ChatRoomViewModelFactory.kt 2020. 5. 30
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.chatroomlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.messenger.repository.chat.ChatRoomRepositoryImpl
/**
 * @author MyeongKi
 */
class ChatRoomViewModelFactory(
    private val repository: ChatRoomRepositoryImpl
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatRoomListViewModel(repository) as T
    }
}