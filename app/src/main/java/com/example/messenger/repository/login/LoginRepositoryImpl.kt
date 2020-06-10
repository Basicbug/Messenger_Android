/*
 * LoginRepositoryImpl.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.login

import com.example.messenger.network.ApiHelper
import com.example.messenger.network.service.login.LoginService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author MyeongKi
 */

class LoginRepositoryImpl : LoginRepository {
    override fun getJwtTokenFromServer(provider: String, accessToken: String): Single<String> {
        return ApiHelper
            .createApiByService(LoginService::class)
            .getJwtToken(provider, accessToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data }
    }

    companion object {

        @Volatile
        private var instance: LoginRepositoryImpl? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: LoginRepositoryImpl().also { instance = it }
            }
    }
}