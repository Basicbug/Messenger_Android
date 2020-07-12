/*
 * MessageType.kt 2020. 5. 1
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.type

/**
 * @author MyeongKi
 */

enum class MessageType(val code: Int?) {
    FAIL(-1),
    MESSAGE(1),
    IMAGE(2);


}