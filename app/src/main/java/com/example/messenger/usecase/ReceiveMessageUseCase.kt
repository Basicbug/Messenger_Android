/*
<<<<<<< HEAD
 * ReceiveMessageUseCase.kt 2020. 5. 11
=======
 * ReceiveMessageUseCase.kt 2020. 5. 19
>>>>>>> develop
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.usecase

import android.util.Log
import com.example.messenger.repository.chat.MessageRepositoryImpl
import com.example.messenger.repository.model.chat.Message
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
                        //TODO 로컬 저장이나 기타 나머지 시나리오...
                    },
                    {
                            err -> Log.e(this.javaClass.simpleName, err.toString())
                    })
        )
    }

    private fun notifyMessage(msg: Message?) {
        msg?.let {
            //TODO 이벤트 발생
        }
    }
}