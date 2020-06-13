/*
 * LoginSuccessUseCase.kt 2020. 6. 13
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.usecase

import android.util.Log
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
                    Log.d("testToken", it.jwtToken?:"")
                }
                .doOnError {
                    Log.d("testToken", it.message?:"")
                }
                .subscribe()
        )
    }
}