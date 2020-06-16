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
import com.example.messenger.usecase.LoginSuccessUseCase

/**
 * @author MyeongKi
 */

class LoginViewModel(
    loginRepository: LoginRepositoryImpl
) : BaseViewModel() {
    init {
        subscribeEvent()
    }
    private val loginSuccessUseCase = LoginSuccessUseCase(loginRepository, disposables)
    private fun subscribeEvent() {
        disposables.add(
            LoginEvent.loginSuccessSubject.subscribe {
                loginSuccessUseCase.successLogin(it)
            }
        )
    }
}