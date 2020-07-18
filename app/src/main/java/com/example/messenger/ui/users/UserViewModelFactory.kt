/*
 * FriendViewModelFactory.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.messenger.repository.user.UserRepositoryImpl

/**
 * @author MyeongKi
 */
class UserViewModelFactory(
    private val repository: UserRepositoryImpl
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            FriendsViewModel::class.java -> {
                FriendsViewModel(repository) as T
            }
            LoginUserViewModel::class.java -> {
                LoginUserViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}