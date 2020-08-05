/*
 * ItemActionEvent.kt 2020. 8. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.common.event

import com.example.messenger.util.bus.RxAction

/**
 * @author MyeongKi
 */

interface ItemActionEvent<T> : RxAction {

    fun getValue(): T
    fun getEventType(): Int
}