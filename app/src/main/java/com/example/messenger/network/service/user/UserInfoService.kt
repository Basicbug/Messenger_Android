/*
 * UserInfoService.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.network.service.user

import com.example.messenger.repository.model.ApiData
import com.example.messenger.repository.model.ApiDataList
import com.example.messenger.repository.model.user.UserInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author MyeongKi
 */

interface UserInfoService {
    @GET("/v1/user/{userId}")
    fun getUserInfo(
        @Path("userId") userId: String
    ): Single<ApiData<UserInfo>>

    @GET("/v1/user/me")
    fun getLoginUserInfo(
    ): Single<ApiData<UserInfo>>

    @GET("/v1/friends/list")
    fun getFriendsInfo(
    ): Single<ApiDataList<UserInfo>>

}