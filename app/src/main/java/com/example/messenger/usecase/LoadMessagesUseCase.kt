package com.example.messenger.usecase

import com.example.messenger.event.ChatRoomEvent
import com.example.messenger.repository.chat.MessageRepositoryImpl
import com.example.messenger.repository.model.chat.Message
import io.reactivex.disposables.CompositeDisposable

/**
 * @author bsgreentea
 */
class LoadMessagesUseCase(
    private val messageRepositoryImpl: MessageRepositoryImpl,
    private val disposables: CompositeDisposable
) {

    fun loadMessages(roomId: String, from: Int) {
        loadLatestFiftyMessages(roomId, from)
    }

    private fun loadLatestFiftyMessages(roomId: String, from: Int) {
        disposables.add(
            messageRepositoryImpl.getLatestFiftyMessages(roomId, from)
                .doOnSuccess {
                    ChatRoomEvent.invokeMessageList(it.reversed())
                }
                .subscribe()
        )
    }

    fun insertMessageToLocal(msg: Message) {
        disposables.add(
            messageRepositoryImpl.insertMessageToLocal(msg)
                .subscribe()
        )
    }
}