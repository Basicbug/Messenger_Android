package com.example.messenger.ui.chattingroom

import com.example.messenger.repository.message.MessageRepositoryInjector

/**
 * @author bsgreentea
 */
object ChattingRoomViewModelInjector {
    fun provideSampleViewModelFactory(
        roomId: String
    ): ChattingRoomViewModelFactory {
        val repository = MessageRepositoryInjector.getMessageRepositoryImpl()
        return ChattingRoomViewModelFactory(repository, roomId)
    }
}