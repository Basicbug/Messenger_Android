/*
 * SampleRepositoryImpl.kt 2020. 4. 22
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.sample

import com.example.messenger.network.ApiHelper
import com.example.messenger.network.service.SampleService
import com.example.messenger.repository.model.Sample
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * @author MyeongKi
 */

class SampleRepositoryImpl : SampleRepository {

    override fun getSample(): Single<Sample> {

        return ApiHelper.createApiByService(SampleService::class).getSample().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}