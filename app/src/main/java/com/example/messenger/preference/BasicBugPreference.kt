/*
 * BasicBugPreference.kt 2020. 6. 14
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.preference

import android.content.Context
import net.grandcentrix.tray.TrayPreferences

/**
 * @author MyeongKi
 */

class BasicBugPreference(context: Context) : TrayPreferences(context, PREFERENCE_NAME, PREFERENCE_VERSION) {

    companion object {
        private const val PREFERENCE_NAME = "basic.bug.preference"
        private const val PREFERENCE_VERSION = 1
    }
}