/*
 * FriendViewModelInjector.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.users

import com.example.messenger.repository.user.UserRepositoryInjector

/**
 * @author MyeongKi
 */
object UserViewModelInjector {
    fun provideUsersViewModelFactory(
    ): UserViewModelFactory {
        val repository = UserRepositoryInjector.getUserRepositoryImpl()
        return UserViewModelFactory(repository)
    }
}