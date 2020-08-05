/*
 * DefaultItemActionEventFactory.kt 2020. 8. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.common.event

import com.example.messenger.type.ItemEvent

/**
 * @author MyeongKi
 */

object DefaultItemActionEventFactory {
    fun createEvent(value:Any, eventType:ItemEvent):DefaultItemActionEvent{
        return when (eventType) {
            ItemEvent.CLICK -> {
                DefaultItemActionEvent(value, ItemEvent.CLICK.type)
            }
            ItemEvent.LONG_CLICK -> {
                DefaultItemActionEvent(value, ItemEvent.LONG_CLICK.type)
            }
            else -> {
                DefaultItemActionEvent(value, ItemEvent.CLICK.type)
            }
        }
    }
}