/*
 * ToastHelper.kt 2020. 6. 29
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.app

import android.widget.Toast
import androidx.annotation.StringRes
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author MyeongKi
 */

object ToastHelper {
    private var toast: Toast? = null

    @JvmStatic
    fun show(@StringRes stringResId: Int) {
        show(AppResources.getStringResId(stringResId))
    }

    @JvmStatic
    fun show(toastMessage: String) {
        cancel()
        AndroidSchedulers.mainThread().scheduleDirect {
            toast = Toast.makeText(AppResources.getContext(), toastMessage, Toast.LENGTH_SHORT).apply {
                show()
            }
        }
    }

    @JvmStatic
    fun cancel() {
        toast?.cancel()
    }
}