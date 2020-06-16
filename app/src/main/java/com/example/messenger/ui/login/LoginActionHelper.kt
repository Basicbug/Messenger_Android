/*
 * LoginActionHelper.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.login

import android.content.Context
import android.content.Intent
import android.view.View
import com.example.messenger.base.BaseHelper
import com.example.messenger.manager.NaverLoginManager
import com.example.messenger.manager.NaverLoginManager.deleteToken
import com.example.messenger.manager.NaverLoginManager.naverLogout
import com.example.messenger.ui.sample.UserInfoActivity
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton

/**
 * @author bsgreentea
 */
class LoginActionHelper : BaseHelper {

    fun onLogoutClick(activity: UserInfoActivity) {
        naverLogout()
        activity.finish()
    }

    fun onDeleteTokenClick(activity: UserInfoActivity) {
        deleteToken()
        activity.finish()
    }

    fun onTestClick(context: Context) {
        context.startActivity(Intent(context, UserInfoActivity::class.java))
    }

    override fun customizePropertiesView(view: View) {
        (view as OAuthLoginButton).setOAuthLoginHandler(NaverLoginManager)
    }
}