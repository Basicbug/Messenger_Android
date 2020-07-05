/*
 * SampleService.kt 2020. 4. 22
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.network.service

import com.example.messenger.repository.model.Sample
import io.reactivex.Single

import retrofit2.http.GET

/**
 * @author MyeongKi
 */

interface SampleService {
    @GET("/test/json")
    fun getSample(): Single<Sample>
}