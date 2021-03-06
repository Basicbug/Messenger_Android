/*
 * LoginViewModelFactory.kt 2020. 6. 13
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.messenger.repository.login.LoginRepositoryImpl
import com.example.messenger.repository.user.UserRepositoryImpl

/**
 * @author MyeongKi
 */
class LoginViewModelFactory(
    private val loginRepository: LoginRepositoryImpl
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(loginRepository) as T
    }
}