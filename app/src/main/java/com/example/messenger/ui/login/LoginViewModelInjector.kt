/*
 * LoginViewModelInjector.kt 2020. 6. 13
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.login

import com.example.messenger.repository.login.LoginRepositoryInjector
import com.example.messenger.repository.user.UserRepositoryInjector

/**
 * @author MyeongKi
 */

object LoginViewModelInjector {
    fun provideLoginViewModelFactory(
    ): LoginViewModelFactory {
        val loginRepository = LoginRepositoryInjector.getLoginRepositoryImpl()
        val userRepository = UserRepositoryInjector.getUserRepositoryImpl()
        return LoginViewModelFactory(loginRepository, userRepository)
    }
}