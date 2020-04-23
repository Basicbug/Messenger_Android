/*
 * ApiBuilder.kt 2020. 4. 23
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * @author MyeongKi
 */

interface ApiBuilder {
    fun builder(builder: OkHttpClient.Builder): Retrofit.Builder
    fun build(): Retrofit
}