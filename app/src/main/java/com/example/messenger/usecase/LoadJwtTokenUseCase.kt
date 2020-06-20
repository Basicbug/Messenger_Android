/*
 * LoginSuccessUseCase.kt 2020. 6. 13
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.usecase

import android.util.Log
import android.widget.Toast
import com.example.messenger.MessengerApp
import com.example.messenger.event.LoginEvent
import com.example.messenger.manager.NaverLoginManager
import com.example.messenger.manager.PreferenceManager
import com.example.messenger.repository.login.LoginRepositoryImpl
import com.example.messenger.repository.model.login.AccessToken
import com.example.messenger.repository.model.login.JwtToken
import com.example.messenger.repository.model.login.Token
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */

class LoadJwtTokenUseCase(
    private val loginRepository: LoginRepositoryImpl,
    private val disposables: CompositeDisposable
) {
    fun loadJwtToken(accessToken: Token) {
        disposables.add(
            loginRepository.getJwtTokenFromServer(accessToken.provider ?: "", accessToken.token ?: "")
                .doOnSuccess { jwt ->
                    PreferenceManager.setJwtToken(jwt.token ?: "")
                    Toast.makeText(MessengerApp.applicationContext(), "로그인되었습니다.", Toast.LENGTH_SHORT).show()
                    LoginEvent.invokeLoadTokenEvent(JwtToken().also { item ->
                        item.token = jwt.token
                    })
                }
                .doOnError {
                    Log.d("testToken", it.message ?: "")
                }
                .subscribe()
        )
    }
}