package com.example.messenger.ui.chat.chatroom

import com.example.messenger.repository.chat.MessageRepositoryInjector

/**
 * @author bsgreentea
 */
object ChatRoomViewModelInjector {
    fun provideSampleViewModelFactory(
        roomId: String
    ): ChatRoomViewModelFactory {
        val repository = MessageRepositoryInjector.getMessageRepositoryImpl()
        return ChatRoomViewModelFactory(repository, roomId)
    }
}