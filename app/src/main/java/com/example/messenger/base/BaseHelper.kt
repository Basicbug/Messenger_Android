/*
 * BaseHelper.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author MyeongKi
 */

interface BaseHelper {
    fun customizePropertiesView(view: View) = Unit
    fun customizeRecyclerView(view: RecyclerView) = Unit
}