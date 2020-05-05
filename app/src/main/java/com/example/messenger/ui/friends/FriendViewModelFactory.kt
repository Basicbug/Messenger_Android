/*
 * FriendViewModelFactory.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.messenger.repository.user.UserRepositoryImpl

/**
 * @author MyeongKi
 */
class FriendViewModelFactory(
    private val repository: UserRepositoryImpl
) : ViewModelProvider.NewInstanceFactory() {

    //TODO 여러뷰모델을 고려하여 팩토리로...
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FriendListViewModel(repository) as T
    }
}