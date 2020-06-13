/*
 * LoginService.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.network.service.login

import com.example.messenger.repository.model.ApiData
import com.example.messenger.repository.model.login.Token
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author MyeongKi
 */
interface LoginService {
    @POST("/v1/social/signin/{provider}")
    fun getJwtToken(
        @Path("provider") provider: String,
        @Query("accessToken") accessToken: String
    ): Single<ApiData<Token>>
}