/*
 * LoginRepositoryInjector.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.login

/**
 * @author MyeongKi
 */
object LoginRepositoryInjector {
    fun getLoginRepositoryImpl(): LoginRepositoryImpl {
        return LoginRepositoryImpl.getInstance()
    }
}