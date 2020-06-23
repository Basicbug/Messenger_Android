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
import com.example.messenger.repository.user.UserRepositoryImpl
import com.example.messenger.usecase.LoginUseCase

/**
 * @author MyeongKi
 */

class LoginViewModel(
    loginRepository: LoginRepositoryImpl,
    userRepository: UserRepositoryImpl
) : BaseViewModel() {
    init {
        subscribeEvent()
    }

    private val loginUseCase = LoginUseCase(loginRepository, userRepository, disposables)
    private fun subscribeEvent() {
        disposables.add(
            LoginEvent.tokenSubject.subscribe {
                if (it is AccessToken) {
                    loginUseCase.loadJwtToken(it)
                }
            }
        )
        disposables.add(
            LoginEvent.statusJwtSavedSubject.subscribe {
                if (it) {
                    loginUseCase.loadLoginUserInfo()
                }
            }
        )
    }
}