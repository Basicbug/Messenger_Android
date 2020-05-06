/*
 * FriendViewModelInjector.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.friends

import com.example.messenger.repository.user.UserRepositoryInjector

/**
 * @author MyeongKi
 */
object FriendViewModelInjector {
    fun provideFriendViewModelFactory(
    ): FriendViewModelFactory {
        val repository = UserRepositoryInjector.getUserRepositoryImpl()
        return FriendViewModelFactory(repository)
    }
}