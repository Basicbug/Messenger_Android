package com.example.messenger.usecase

import com.example.messenger.constants.AppInfoConstants
import com.example.messenger.event.ChattingRoomEvent
import com.example.messenger.repository.message.MessageRepositoryImpl
import com.example.messenger.repository.model.Message
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

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
                    ChattingRoomEvent.addMessagesToList(it.reversed())
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