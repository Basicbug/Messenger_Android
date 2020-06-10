/*
 * LoginSuccessEvent.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.event

import io.reactivex.subjects.PublishSubject

/**
 * @author MyeongKi
 */
object LoginEvent {
    val loginSuccessSubject = PublishSubject.create<String>()
    fun invokeLoginSuccess(token: String) {
        loginSuccessSubject.onNext(token)
    }
}