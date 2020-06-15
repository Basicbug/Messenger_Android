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
import com.example.messenger.manager.PreferenceManager
import com.example.messenger.repository.login.LoginRepositoryImpl
import com.example.messenger.repository.model.login.Token
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */

class LoginSuccessUseCase(
    private val loginRepository: LoginRepositoryImpl,
    private val disposables: CompositeDisposable
) {
    fun successLogin(token: Token) {
        disposables.add(
            loginRepository.getJwtTokenFromServer(token.provider ?: "", token.accessToken ?: "")
                .doOnSuccess {
                    PreferenceManager.setJwtToken(it.jwtToken ?: "")
                    Toast.makeText(MessengerApp.applicationContext(), "로그인되었습니다."+PreferenceManager.getJwtToken(), Toast.LENGTH_SHORT).show()
                }
                .doOnError {
                    Log.d("testToken", it.message ?: "")
                }
                .subscribe()
        )
    }
}