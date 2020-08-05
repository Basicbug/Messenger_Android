/*
 * DefalutItemActionEvent.kt 2020. 8. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.common.event

/**
 * @author MyeongKi
 */

class DefaultItemActionEvent(private val value: Any, private val eventType: Int) : ItemActionEvent<Any> {
    override fun getValue(): Any {
        return value
    }

    override fun getEventType(): Int {
        return eventType
    }

}