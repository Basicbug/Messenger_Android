/*
 * LoginActionHelper.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.login

import android.view.View
import android.widget.Toast
import com.example.messenger.R
import com.example.messenger.app.MessengerApp
import com.example.messenger.app.ToastHelper
import com.example.messenger.base.BaseActivity
import com.example.messenger.base.BaseHelper
import com.example.messenger.event.LoginEvent
import com.example.messenger.manager.NaverLoginManager
import com.example.messenger.manager.PreferenceManager
import com.example.messenger.repository.model.login.JwtToken
import com.example.messenger.type.LoginResultType
import com.example.messenger.ui.start.MainHolderActivity
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton
import io.reactivex.disposables.CompositeDisposable

/**
 * @author bsgreentea
 */
class LoginActivityHelper(
    private val activity: BaseActivity,
    private val disposable: CompositeDisposable,
    private val loadingLoginViewModel: LoadingLoginViewModel
) :
    BaseHelper {

    init {
        checkLogin(LoginResultType.ALREADY)
        observeEvent()
    }

    private fun observeEvent() {
        disposable.add(
            LoginEvent.tokenSubject.subscribe {
                if (it is JwtToken) {
                    checkLogin(LoginResultType.SUCCESS)
                }
            }
        )
        disposable.add(
            LoginEvent.loginResultSubject.subscribe {
                handleLoginResult(it)
            }
        )
    }

    private fun checkLogin(loginResult: LoginResultType) {
        val jwtToken = PreferenceManager.getJwtToken()
        jwtToken?.let {
            NaverLoginManager.setJwtToken(jwtToken)
            handleLoginResult(loginResult)
        }
    }

    private fun handleLoginResult(loginResult: LoginResultType) {
        loadingLoginViewModel.loginPageVisible = true
        when (loginResult) {
            LoginResultType.SUCCESS -> {
                ToastHelper.show(R.string.login_success_message)
                startMainHolder()
            }
            LoginResultType.FAIL -> {
                ToastHelper.show(R.string.login_fail_message)
            }
            LoginResultType.ALREADY -> {
                startMainHolder()
            }
        }
    }

    private fun startMainHolder() {
        activity.startActivity(MainHolderActivity::class.java)
        activity.finish()
    }

    override fun customizePropertiesView(view: View) {
        (view as OAuthLoginButton).setOAuthLoginHandler(NaverLoginManager)
    }
}