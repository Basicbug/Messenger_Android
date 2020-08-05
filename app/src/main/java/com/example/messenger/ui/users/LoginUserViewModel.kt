/*
 * LoginUserViewModel.kt 2020. 7. 18
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.users

import com.example.messenger.base.BaseViewModel
import com.example.messenger.event.UserEvent
import com.example.messenger.repository.user.UserRepositoryImpl
import com.example.messenger.ui.users.adapter.UserItemViewModel
import com.example.messenger.usecase.LoadLoginUserUseCase

/**
 * @author MyeongKi
 */

class LoginUserViewModel(
    userRepository: UserRepositoryImpl
) : BaseViewModel() {
    val loadLoginUserUseCase = LoadLoginUserUseCase(userRepository, disposables)
    private val userItemViewModel = UserItemViewModel()

    init {
        subscribeEvent()
    }

    fun getItemViewModel(): UserItemViewModel {
        return userItemViewModel
    }

    private fun subscribeEvent() {
        disposables.add(
            UserEvent.loginUserInfoSubject.subscribe {
                userItemViewModel.userInfo = it
            }
        )
    }
}