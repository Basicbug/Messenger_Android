/*
 * RxBus.kt 2020. 8. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.util.bus

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay

/**
 * @author MyeongKi
 */

object RxBus {
    private val bus: Relay<Any> = PublishRelay.create()

    fun post(event: Any) {
        bus.accept(event)
    }
}