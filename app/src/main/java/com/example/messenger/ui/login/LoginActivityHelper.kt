/*
 * LoginActionHelper.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.login

import android.view.View
import android.widget.Toast
import com.example.messenger.MessengerApp
import com.example.messenger.base.BaseActivity
import com.example.messenger.base.BaseHelper
import com.example.messenger.event.LoginEvent
import com.example.messenger.manager.NaverLoginManager
import com.example.messenger.manager.PreferenceManager
import com.example.messenger.repository.model.login.JwtToken
import com.example.messenger.ui.start.MainHolderActivity
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton
import io.reactivex.disposables.CompositeDisposable

/**
 * @author bsgreentea
 */
class LoginActivityHelper(private val activity: BaseActivity, private val disposable: CompositeDisposable) :
    BaseHelper {

    init {
        checkLogin()
        observeEvent()
    }

    private fun observeEvent() {
        disposable.add(
            LoginEvent.tokenSubject.subscribe {
                if (it is JwtToken) {
                    checkLogin()
                }
            }
        )
    }

    private fun checkLogin() {
        val jwtToken = PreferenceManager.getJwtToken()
        jwtToken?.let {
            NaverLoginManager.setJwtToken(jwtToken)
            Toast.makeText(MessengerApp.applicationContext(), "로그인되었습니다.", Toast.LENGTH_SHORT).show()
            startMainHolder()
        }
    }
    private fun startMainHolder(){
        activity.startActivity(MainHolderActivity::class.java)
        activity.finish()
    }
    override fun customizePropertiesView(view: View) {
        (view as OAuthLoginButton).setOAuthLoginHandler(NaverLoginManager)
    }
}