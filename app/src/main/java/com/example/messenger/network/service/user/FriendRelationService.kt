/*
 * FriendRelationService.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.network.service.user

import com.example.messenger.repository.model.ApiDataList
import com.example.messenger.repository.model.user.FriendRelation
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author MyeongKi
 */

interface FriendRelationService {
    @GET("/v1/friends/{userId}")
    fun getFriendRelationList(
        @Path("userId") userId: String
    ): Single<ApiDataList<FriendRelation>>
}