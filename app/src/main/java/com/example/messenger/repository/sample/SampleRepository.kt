/*
 * SampleRepository.kt 2020. 4. 22
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.sample

import io.reactivex.Single


/**
 * @author MyeongKi
 */

interface SampleRepository {
    fun getSample(): Single<String>
}