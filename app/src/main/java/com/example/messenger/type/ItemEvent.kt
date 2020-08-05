/*
 * ItemEvent.kt 2020. 8. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.type

/**
 * @author MyeongKi
 */

enum class ItemEvent(val type: Int) {
    NONE(-1),
    CLICK(1),
    LONG_CLICK(2);
}