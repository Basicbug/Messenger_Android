/*
 * UserService.kt 2020. 5. 4
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.network.service

import com.example.messenger.repository.model.User
import io.reactivex.Single
import retrofit2.http.GET

/**
 * @author MyeongKi
 */

interface UserService {
    @GET("")
    fun getFriends(userId:String): Single<ArrayList<User>>
}