/*
 * LoginViewModelInjector.kt 2020. 6. 13
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.login

import com.example.messenger.repository.login.LoginRepositoryInjector

/**
 * @author MyeongKi
 */

object LoginViewModelInjector {
    fun provideLoginViewModelFactory(
    ): LoginViewModelFactory {
        val repository = LoginRepositoryInjector.getLoginRepositoryImpl()
        return LoginViewModelFactory(repository)
    }
}