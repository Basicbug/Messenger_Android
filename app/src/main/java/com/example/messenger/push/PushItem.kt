/*
 * PushItem.kt 2020. 4. 29
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.push

/**
 * @author MyeongKi
 */
interface PushItem {
    fun getTitle(): String
    fun getContent(): String
}