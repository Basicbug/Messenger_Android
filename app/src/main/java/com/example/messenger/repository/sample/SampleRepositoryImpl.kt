/*
 * SampleRepositoryImpl.kt 2020. 4. 22
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.sample

import com.example.messenger.database.sample.SampleDatabase
import com.example.messenger.network.ApiHelper
import com.example.messenger.network.service.SampleService
import com.example.messenger.repository.model.Sample
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author MyeongKi
 */

class SampleRepositoryImpl : SampleRepository {

    private val sampleDao = SampleDatabase.getDatabase().sampleDao()

    override fun getSampleFromServer(): Single<Sample> {
        return ApiHelper
            .createApiByService(SampleService::class)
            .getSample()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    override fun getSampleFromLocal(): Single<Sample> {
        return sampleDao.getSample()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).map { it[0] }
    }

    override fun saveSample(sample: Sample?): Completable {
        return sampleDao.insertSample(sample)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: SampleRepositoryImpl? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: SampleRepositoryImpl().also { instance = it }
            }
    }
}