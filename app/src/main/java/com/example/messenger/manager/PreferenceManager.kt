/*
 * PreferenceManager.kt 2020. 6. 14
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.manager

import com.example.messenger.MessengerApp
import com.example.messenger.constants.PreferenceKeyConstants.JWT_TOKEN_KEY
import com.example.messenger.preference.BasicBugPreference

/**
 * @author MyeongKi
 */

object PreferenceManager {
    private val preference = BasicBugPreference(MessengerApp.applicationContext())

    fun getJwtToken(): String? {
        return preference.getString(JWT_TOKEN_KEY)
    }

    fun setJwtToken(jwtToken: String) {
        preference.put(JWT_TOKEN_KEY, jwtToken)
    }
}