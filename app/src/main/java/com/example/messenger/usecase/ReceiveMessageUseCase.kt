/*
 * ReceiveMessageUseCase.kt 2020. 5. 19
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.usecase

import android.util.Log
import com.example.messenger.event.ChattingRoomEvent
import com.example.messenger.repository.message.MessageRepositoryImpl
import com.example.messenger.repository.model.Message
import com.example.messenger.tools.convertJsonStringToMessage
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */

class ReceiveMessageUseCase(
    private val messageRepositoryImpl: MessageRepositoryImpl,
    private val disposables: CompositeDisposable
) {
    fun subscribeChattingRoom(roomId: String) {
        disposables.add(
            messageRepositoryImpl.subscribeChattingRoom(roomId)
                .subscribe(
                    { stompMessage ->
                        val msg = convertJsonStringToMessage(stompMessage.payload)
                        notifyMessage(msg)

                        msg?.let {
                            messageRepositoryImpl.insertMessageToLocal(it)
                            ChattingRoomEvent.addMessageToList(it)
                        }

                    },
                    { err ->
                        Log.e(this.javaClass.simpleName, err.toString())
                    })
        )
    }

    private fun notifyMessage(msg: Message?) {
        msg?.let {
            //TODO 이벤트 발생
        }
    }

    fun testReceiveMessage(msg: Message) {
        disposables.add(
            messageRepositoryImpl.insertMessageToLocal(msg).subscribe()
        )
        ChattingRoomEvent.addMessageToList(msg)
    }
}