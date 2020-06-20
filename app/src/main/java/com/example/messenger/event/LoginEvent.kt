/*
 * LoginSuccessEvent.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.event

import com.example.messenger.repository.model.login.Token
import io.reactivex.subjects.PublishSubject

/**
 * @author MyeongKi
 */
object LoginEvent {
    val loadTokenSubject = PublishSubject.create<Token>()
    fun invokeLoadTokenEvent(token: Token) {
        loadTokenSubject.onNext(token)
    }
}