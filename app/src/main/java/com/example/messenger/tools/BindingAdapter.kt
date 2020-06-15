/*
 * BindingAdapter.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.tools

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.messenger.base.BaseHelper


/**
 * @author MyeongKi
 */

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setOnListenerFromHelper")
    fun setOnListenerFromHelper(
        view: View, helper: BaseHelper
    ) {
        helper.customizePropertiesView(view)
    }

    @JvmStatic
    @BindingAdapter("setManagerFromHelper")
    fun setManagerFromHelper(
        view: View, helper: BaseHelper
    ) {
        helper.customizePropertiesView(view)
    }
}