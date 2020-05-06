/*
 * NavigationDelegate.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.tools

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * @author MyeongKi
 */

interface NavigationDelegate {
    fun replaceFragment(target: Class<out Fragment>, containerId:Int, arguments: Bundle? = null)
    fun replaceFragmentSaved(target: Class<out Fragment>, containerId:Int, arguments: Bundle? = null)
    fun startActivity(target: Class<out Activity>, extras: Bundle? = null)
}