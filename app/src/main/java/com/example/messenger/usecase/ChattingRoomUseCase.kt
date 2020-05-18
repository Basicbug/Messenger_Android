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
    private val roomID: Int,
    private val messageRepository: MessageRepositoryImpl,
    private val disposables: CompositeDisposable
) : BaseUseCase {

    override fun execute() {
//        getMessageHistoryFromLocal(roomID)
        getTestMessageHistoryInfoFromLocal()
    }

    private fun getMessageHistoryFromLocal() {
//        disposables.add(
//            messageRepository.getMessageListFromLocalBetweenTags(roomID)
//                .doOnSuccess {
//                    for (message in it) {
//                        message.roomID?.let {
//
//                        }
//                    }
//                }
//                .subscribe()
//        )
    }

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

//    private fun insertMessageToLocal(message: Message) {
//        disposables.add(
//            messageRepository.insertMessageToLocal(message)
//                .subscribe()
//        )
//    }

}