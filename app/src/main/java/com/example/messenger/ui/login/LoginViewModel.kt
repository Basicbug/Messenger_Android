/*
 * LoginViewModel.kt 2020. 6. 13
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.login

import com.example.messenger.base.BaseViewModel
import com.example.messenger.event.LoginEvent
import com.example.messenger.repository.login.LoginRepositoryImpl
import com.example.messenger.repository.model.login.AccessToken
import com.example.messenger.usecase.LoginUseCase

/**
 * @author MyeongKi
 */

class LoginViewModel(
    loginRepository: LoginRepositoryImpl
) : BaseViewModel() {
    val loadingLoginViewModel = LoadingLoginViewModel()

    init {
        subscribeEvent()
    }

    private val loginUseCase = LoginUseCase(loginRepository, disposables)
    private fun subscribeEvent() {
        disposables.add(
            LoginEvent.tokenSubject.subscribe {
                if (it is AccessToken) {
                    setLoginPageVisible(false)
                    loginUseCase.loadJwtToken(it)
                }
            }
        )
    }

    fun setLoginPageVisible(visible: Boolean) {
        loadingLoginViewModel.loginPageVisible = visible
    }
}