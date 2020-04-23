/*
 * RestApiBuilder.kt 2020. 4. 23
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
abstract class RestApiBuilder :ApiBuilder{
    override fun build(): Retrofit {
        return builder(OkHttpClient().newBuilder()).build()
    }

}