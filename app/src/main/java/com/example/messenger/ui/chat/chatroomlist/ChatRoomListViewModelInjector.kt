/*
 * ChatRoomListViewModelInjector.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.chat.chatroomlist

import com.example.messenger.repository.chat.ChatRoomRepositoryInjector
import com.example.messenger.repository.chat.MessageRepositoryInjector

/**
 * @author MyeongKi
 */
object ChatRoomListViewModelInjector {
    fun provideChatRoomViewModelFactory(
    ): ChatRoomViewModelFactory {
        val charRoomRepository = ChatRoomRepositoryInjector.getChatRoomRepositoryImpl()
        val messageRepository = MessageRepositoryInjector.getMessageRepositoryImpl()

        return ChatRoomViewModelFactory(charRoomRepository, messageRepository)
    }
}