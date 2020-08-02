/*
 * LoginRepository.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.login

import com.example.messenger.repository.model.ApiData
import com.example.messenger.repository.model.login.JwtToken
import com.example.messenger.repository.model.login.Token
import io.reactivex.Single

/**
 * @author MyeongKi
 */

interface LoginRepository {
    fun getJwtTokenFromServer(provider:String, accessToken: String): Single<ApiData<JwtToken>>
}