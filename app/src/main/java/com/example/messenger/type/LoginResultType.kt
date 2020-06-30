/*
 * LoginResultType.kt 2020. 6. 29
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.type

/**
 * @author MyeongKi
 */

enum class LoginResultType(val result: Int) {
    FAIL(-1),
    SUCCESS(1),
    ALREADY(2);


}