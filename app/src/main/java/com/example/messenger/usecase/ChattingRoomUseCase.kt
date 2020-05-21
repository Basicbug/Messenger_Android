package com.example.messenger.usecase

import com.example.messenger.base.BaseUseCase
import com.example.messenger.event.ChattingRoomEvent
import com.example.messenger.repository.message.MessageRepositoryImpl
import com.example.messenger.repository.model.Message
import io.reactivex.disposables.CompositeDisposable

/**
 * @author bsgreentea
 */
class ChattingRoomUseCase(
    private val roomID: String,
    private val messageRepository: MessageRepositoryImpl,
    private val disposables: CompositeDisposable
) {





    private fun getTestMessageHistoryInfoFromLocal() {
        disposables.add(
            messageRepository.getMessageListFromLocal(roomID)
                .doOnSuccess {
                    for (message in it) {
                        ChattingRoomEvent.addMessageToList(message)
                    }
                }
                .subscribe()
        )
    }

}