/*
 * UserRepositoryInjector.kt 2020. 5. 4
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.user

/**
 * @author MyeongKi
 */
object UserRepositoryInjector {
    fun getUserRepositoryImpl(): UserRepositoryImpl {
        return UserRepositoryImpl.getInstance()
    }
}