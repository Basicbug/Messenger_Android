/*
 * RxActionBus.kt 2020. 8. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.util.bus

/**
 * @author MyeongKi
 */

object RxActionBus {
    fun post(event : RxAction){
        RxBus.post(event)
    }
}