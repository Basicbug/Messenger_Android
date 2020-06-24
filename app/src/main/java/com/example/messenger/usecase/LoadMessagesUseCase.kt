package com.example.messenger.usecase

import com.example.messenger.event.ChattingRoomEvent
import com.example.messenger.repository.message.MessageRepositoryImpl
import com.example.messenger.repository.model.chat.Message
import io.reactivex.disposables.CompositeDisposable

/**
 * @author bsgreentea
 */
class LoadMessagesUseCase(
    private val messageRepository: MessageRepositoryImpl,
    private val disposables: CompositeDisposable
) {

    fun loadMessages(roomId: String, from: Int) {
        loadLatestFiftyMessages(roomId, from)
    }

    private fun loadLatestFiftyMessages(roomId: String, from: Int) {
        disposables.add(
            messageRepository.getLatestFiftyMessages(roomId, from)
                .doOnSuccess {
                    ChattingRoomEvent.invokeMessageList(it.reversed())
                }
                .subscribe()
        )
    }

    fun insertMessageToLocal(msg: Message) {
        disposables.add(
            messageRepository.insertMessageToLocal(msg)
                .subscribe()
        )
    }
}