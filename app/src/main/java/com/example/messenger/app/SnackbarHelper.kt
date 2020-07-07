/*
 * SnackbarHelper.kt 2020. 7. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.app

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.example.messenger.R
import com.google.android.material.snackbar.Snackbar

/**
 * @author MyeongKi
 */

object SnackbarHelper {
    fun show(activity: Activity, @StringRes stringResId: Int) {
        show(activity, AppResources.getStringResId(stringResId))
    }

    fun show(activity: Activity, snackbarMessage: String) {
        val decorView = getDecorView(activity)
        val anchorView = getAnchorView(decorView)
        anchorView?.let {
            val snackbar = Snackbar.make(it, snackbarMessage, Snackbar.LENGTH_INDEFINITE)
            snackbar.setAction(R.string.confirm) {
                snackbar.dismiss()
            }
            snackbar.show()
        }
    }

    private fun getAnchorView(view: ViewGroup?): View? {
        return view?.findViewById(R.id.constraint_layout)
    }

    private fun getDecorView(activity: Activity): ViewGroup? {
        if (activity.isFinishing) {
            return null
        }
        val window = activity.window
        window?.let {
            return it.decorView as ViewGroup
        }
        return null
    }
}