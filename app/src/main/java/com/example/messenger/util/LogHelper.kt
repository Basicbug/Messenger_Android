/*
 * LogHelper.kt 2020. 7. 25
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.util

import android.util.Log

/**
 * @author MyeongKi
 */

fun errorLog(errorObjectInvoked: Any, error: Throwable) {
    Log.e(errorObjectInvoked.javaClass.simpleName, error.message ?: "")
}