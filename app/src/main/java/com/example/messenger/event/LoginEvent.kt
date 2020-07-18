/*
 * LoginSuccessEvent.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.event

import com.example.messenger.repository.model.login.Token
import com.example.messenger.repository.model.user.UserInfo
import com.example.messenger.type.LoginResultType
import io.reactivex.subjects.PublishSubject

/**
 * @author MyeongKi
 */
object LoginEvent {
    val tokenSubject = PublishSubject.create<Token>()
    val loginResultSubject = PublishSubject.create<LoginResultType>()

    fun invokeToken(token: Token) {
        tokenSubject.onNext(token)
    }

    fun invokeLoginResult(loginResult: LoginResultType) {
        loginResultSubject.onNext(loginResult)
    }


}