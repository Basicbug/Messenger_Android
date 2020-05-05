package com.example.messenger.ui.sample.helper

import android.content.Context
import android.content.Intent
import com.example.messenger.NaverLoginManager.deleteToken
import com.example.messenger.NaverLoginManager.naverLogout
import com.example.messenger.ui.sample.UserInfoActivity

/**
 * @author bsgreentea
 */
class LoginActionHelper {

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
}