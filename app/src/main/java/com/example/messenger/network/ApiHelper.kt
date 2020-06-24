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
import com.example.messenger.manager.NaverLoginManager
import com.example.messenger.network.service.login.LoginService
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
        return if (clazz is LoginService) {
            build(false).create(clazz.java)
        } else {
            build(true).create(clazz.java)
        }
    }

    private fun build(needJwtToken: Boolean): Retrofit {
        val okHttpClient = OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor())
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(MessengerApp.applicationContext())
        }
        if (needJwtToken) {
            okHttpClient.addInterceptor {
                val requestBuilder = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${NaverLoginManager.jwtToken?.token ?: ""}")
                it.proceed(requestBuilder.build())
            }
        }
        return Retrofit
            .Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient.build())
            .build()
    }

}