/*
 * SampleRepository.kt 2020. 4. 22
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.sample

import com.example.messenger.repository.model.Sample
import io.reactivex.rxjava3.core.Single

/**
 * @author MyeongKi
 */

interface SampleRepository {
    fun getSample(): Single<Sample>
}