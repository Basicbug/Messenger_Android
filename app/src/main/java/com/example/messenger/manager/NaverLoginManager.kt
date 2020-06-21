/*
 * NaverLoginManager.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.manager

import android.util.Log
import android.widget.Toast
import com.example.messenger.MessengerApp
import com.example.messenger.constants.AppInfoConstants
import com.example.messenger.event.LoginEvent
import com.example.messenger.repository.model.login.AccessToken
import com.example.messenger.repository.model.login.JwtToken
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author bsgreentea
 */

object NaverLoginManager : OAuthLoginHandler() {

    private const val PROVIDER = "naver"
    private const val OAUTH_CLIENT_ID = "nJg0Pj0f2rEBCBQSZe6s"
    private const val OAUTH_CLIENT_SECRET = "2Ca8FHXsj4"
    var jwtToken: JwtToken? = null
    private val context = MessengerApp.applicationContext()
    private val loginInstance = OAuthLogin.getInstance().apply {
        init(
            context,
            OAUTH_CLIENT_ID,
            OAUTH_CLIENT_SECRET,
            AppInfoConstants.AppName
        )
    }

    fun loginInstance(): OAuthLogin {
        return loginInstance
    }

    fun naverLogout() {
        loginInstance?.logout(context)
    }

    override fun run(success: Boolean) {
        if (success) {
            val accessToken = loginInstance?.getAccessToken(
                context
            ).toString()
            LoginEvent.invokeLoadTokenEvent(AccessToken().also {
                it.token = accessToken
                it.provider = PROVIDER
            })
        } else {
            val errorCode = loginInstance?.getLastErrorCode(
                context
            )?.code.toString()
            val descCode = loginInstance?.getLastErrorDesc(
                context
            ).toString()
            Toast.makeText(context, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteToken() {
        Schedulers.newThread().createWorker().schedule {
            loginInstance.logoutAndDeleteToken(
                context
            )
        }
    }

    fun setJwtToken(token: String) {
        jwtToken = JwtToken().apply {
            this.token = token
        }
    }

    private fun getObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onComplete() {
                Toast.makeText(context, "탈퇴되었습니다", Toast.LENGTH_SHORT).show()
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                loginInstance.logoutAndDeleteToken(
                    context
                )
                val errorCode = loginInstance?.getLastErrorCode(
                    context
                )?.code.toString()
                val descCode = loginInstance?.getLastErrorDesc(
                    context
                ).toString()
                Log.d("delete_error", errorCode + " / " + descCode)
            }

            override fun onError(e: Throwable) {
//                Toast.
            }
        }
    }
}