/*
 * RxBus.kt 2020. 8. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.util.bus

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

/**
 * @author MyeongKi
 */

object RxBus {
    private val bus: Relay<Any> = PublishRelay.create()

    fun post(event: Any) {
        bus.accept(event)
    }

    fun <T> toObservable(eventType: Class<T>): Observable<T> {
        return bus.ofType(eventType)
    }

    fun toObservable(): Observable<Any> {
        return bus
    }
}