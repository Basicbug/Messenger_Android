/*
 * BindableItem.kt 2020. 6. 7
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.base

import androidx.annotation.LayoutRes

/**
 * @author MyeongKi
 */

interface BindableLayout{
    @LayoutRes
    fun getLayoutRes(): Int
}