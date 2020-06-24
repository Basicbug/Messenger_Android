package com.example.messenger.ui.chatroom

import com.example.messenger.repository.message.MessageRepositoryInjector

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