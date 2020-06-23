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
import com.example.messenger.manager.PreferenceManager
import com.example.messenger.repository.login.LoginRepositoryImpl
import com.example.messenger.repository.model.login.JwtToken
import com.example.messenger.repository.model.login.Token
import com.example.messenger.repository.user.UserRepositoryImpl
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */

class LoginUseCase(
    private val loginRepository: LoginRepositoryImpl,
    private val userRepositoryImpl: UserRepositoryImpl,
    private val disposables: CompositeDisposable
) {

    fun loadJwtToken(accessToken: Token) {
        disposables.add(
            loginRepository.getJwtTokenFromServer(accessToken.provider ?: "", accessToken.token ?: "")
                .doOnSuccess { jwt ->
                    PreferenceManager.setJwtToken(jwt.token ?: "")
                    LoginEvent.invokeToken(JwtToken().also { item ->
                        item.token = jwt.token
                    })
                }
                .doOnError {
                    Log.d(this.javaClass.simpleName, it.message ?: "")
                }
                .subscribe()
        )
    }
    fun loadLoginUserInfo(){
        disposables.add(
            userRepositoryImpl.getLoginUserInfoFromServer()
                .doOnSuccess {
                    LoginEvent.invokeLoginUserInfo(it)
                }
                .doOnError {
                    Log.d(this.javaClass.simpleName, it.message ?: "")
                }
                .subscribe()
        )
    }
}