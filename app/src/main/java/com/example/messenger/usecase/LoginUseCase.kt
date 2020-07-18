/*
 * LoginSuccessUseCase.kt 2020. 6. 13
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.usecase

import android.util.Log
import com.example.messenger.event.LoginEvent
import com.example.messenger.manager.PreferenceManager
import com.example.messenger.repository.login.LoginRepositoryImpl
import com.example.messenger.repository.model.login.JwtToken
import com.example.messenger.repository.model.login.Token
import com.example.messenger.type.LoginResultType
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */

class LoginUseCase(
    private val loginRepository: LoginRepositoryImpl,
    private val disposables: CompositeDisposable
) {
    fun loadJwtToken(accessToken: Token) {
        disposables.add(
            loginRepository.getJwtTokenFromServer(accessToken.provider ?: "", accessToken.token ?: "")
                .subscribe(
                    { jwt ->
                        PreferenceManager.setJwtToken(jwt.token ?: "")
                        LoginEvent.invokeToken(JwtToken().also { item ->
                            item.token = jwt.token
                        })
                    },
                    { error ->
                        Log.e(this.javaClass.simpleName, error.message ?: "")
                        LoginEvent.invokeLoginResult(LoginResultType.FAIL)
                    })
        )
    }
}