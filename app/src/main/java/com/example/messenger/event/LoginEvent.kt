/*
 * LoginSuccessEvent.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.event

import com.example.messenger.repository.model.login.Token
import com.example.messenger.repository.model.user.UserInfo
import io.reactivex.subjects.PublishSubject

/**
 * @author MyeongKi
 */
object LoginEvent {
    val tokenSubject = PublishSubject.create<Token>()

    fun invokeToken(token: Token) {
        tokenSubject.onNext(token)
    }
}