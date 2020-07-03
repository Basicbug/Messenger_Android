/*
 * PreferenceManager.kt 2020. 6. 14
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.manager

import com.example.messenger.app.AppResources
import com.example.messenger.app.MessengerApp
import com.example.messenger.constants.PreferenceKeyConstants.JWT_TOKEN_KEY
import com.example.messenger.preference.BasicBugPreference

/**
 * @author MyeongKi
 */

object PreferenceManager {
    private val preference = BasicBugPreference(AppResources.getContext())

    fun getJwtToken(): String? {
        return preference.getString(JWT_TOKEN_KEY, null)
    }

    fun setJwtToken(jwtToken: String) {
        preference.put(JWT_TOKEN_KEY, jwtToken)
    }
}