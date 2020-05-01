/*
 * ApiHelper.kt 2020. 4. 23
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.network

import com.example.messenger.BuildConfig
import com.example.messenger.MessengerApp
import com.example.messenger.constants.NetworkConstants
import com.facebook.stetho.Stetho

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

/**
 * @author MyeongKi
 */

object ApiHelper {
    @JvmStatic
    fun <T : Any> createApiByService(clazz: KClass<T>): T {
        return build().create(clazz.java)
    }

    private fun build(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(MessengerApp.applicationContext())
        }
        return Retrofit
            .Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    }
}