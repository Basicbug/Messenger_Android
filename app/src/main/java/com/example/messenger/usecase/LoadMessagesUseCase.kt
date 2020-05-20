package com.example.messenger.usecase

import com.example.messenger.event.ChattingRoomEvent
import com.example.messenger.repository.message.MessageRepositoryImpl
import io.reactivex.disposables.CompositeDisposable

/**
 * @author bsgreentea
 */
class LoadMessagesUseCase(
    private val messageRepository: MessageRepositoryImpl,
    private val disposables: CompositeDisposable
) {

    fun loadMessages(roomId: Int) {
        getLatestFiftyMessages(roomId, 0)
    }

    private fun getLatestFiftyMessages(roomId: Int, from: Int) {
        disposables.add(
            messageRepository.getLatestFiftyMessages(roomId, from)
                .doOnSuccess {
                    for (message in it) {
                        ChattingRoomEvent.addMessageToList(message)
                    }
                }
                .subscribe()
        )
    }

    private fun getTestMessageHistoryInfoFromLocal(roomId: Int) {
        disposables.add(
            messageRepository.getMessageListFromLocal(roomId)
                .doOnSuccess {
                    for (message in it) {
                        ChattingRoomEvent.addMessageToList(message)
                    }
                }
                .subscribe()
        )
    }

}