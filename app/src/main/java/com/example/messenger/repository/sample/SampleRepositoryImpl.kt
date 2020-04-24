/*
 * SampleRepositoryImpl.kt 2020. 4. 22
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.sample

import android.content.Context
import com.example.messenger.database.sample.SampleDatabase
import com.example.messenger.network.ApiHelper
import com.example.messenger.network.service.SampleService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author MyeongKi
 */

class SampleRepositoryImpl(context: Context) : SampleRepository {

    val sampleDao = SampleDatabase.getDatabase(context).sampleDao()

    override fun getSample(): Single<String> {
        return ApiHelper
            .createApiByService(SampleService::class)
            .getSample()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.email }

    }


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: SampleRepositoryImpl? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: SampleRepositoryImpl(context).also { instance = it }
            }
    }
}