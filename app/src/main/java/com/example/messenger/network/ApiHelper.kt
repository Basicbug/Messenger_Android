/*
 * ApiHelper.kt 2020. 4. 23
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.network

import com.example.messenger.BuildConfig
import com.example.messenger.constants.BASE_URL

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

/**
 * @author MyeongKi
 */

object ApiHelper : RestApiBuilder() {
    @JvmStatic
    fun <T : Any> createApiByService(clazz: KClass<T>): T {
        return build().create(clazz.java)
    }

    override fun builder(builder: OkHttpClient.Builder): Retrofit.Builder {

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(StethoInterceptor())
        }
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(builder.build())
    }
}