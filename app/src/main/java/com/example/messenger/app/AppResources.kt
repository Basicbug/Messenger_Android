/*
 * AppResources.kt 2020. 6. 29
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.app

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes

/**
 * @author MyeongKi
 */

object AppResources {
    @JvmStatic
    fun getContext(): Context {
        return MessengerApp.applicationContext()
    }

    @JvmStatic
    fun getResources(): Resources {
        return getContext().resources
    }

    @JvmStatic
    fun getStringResId(@StringRes resId: Int): String {
        return getResources().getString(resId)
    }

}