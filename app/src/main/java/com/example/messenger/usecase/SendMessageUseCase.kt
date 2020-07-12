/*
 * SendMessageUseCase.kt 2020. 5. 19
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.usecase

import android.util.Log
import com.example.messenger.repository.chat.MessageRepositoryImpl
import com.example.messenger.repository.model.chat.Message
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */
class SendMessageUseCase(
    private val messageRepositoryImpl: MessageRepositoryImpl,
    private val disposables: CompositeDisposable
) {
    fun sendMessage(msg: Message) {
        sendMessageToSocketServer(msg)
    }

    private fun sendMessageToSocketServer(msg: Message) {
        disposables.add(
            messageRepositoryImpl.sendMessageToSocketServer(msg).subscribe(
                {},//TODO 여기에는 성공시 이후 시나리오
                { err -> Log.e(this.javaClass.simpleName, err.toString()) }
            )
        )
    }
}