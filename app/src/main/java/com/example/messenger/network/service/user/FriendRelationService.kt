/*
 * FriendRelationService.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.network.service.user

import android.util.ArrayMap
import com.example.messenger.repository.model.user.FriendRelation
import io.reactivex.Single
import retrofit2.http.GET

/**
 * @author MyeongKi
 */

interface FriendRelationService {
    @GET("")
    fun getFriendRelationList(userId: String): Single<ArrayMap<String, ArrayList<FriendRelation>>>
}