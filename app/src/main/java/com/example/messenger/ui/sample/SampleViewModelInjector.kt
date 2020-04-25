/*
 * SampleViewModelInjector.kt 2020. 4. 25
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.sample

import com.example.messenger.repository.sample.SampleRepositoryInjector

/**
 * @author MyeongKi
 */

object SampleViewModelInjector {
    fun provideSampleViewModelFactor(
    ): SampleViewModelFactory {
        val repository = SampleRepositoryInjector.getSampleRepositoryImpl()
        return SampleViewModelFactory(repository)
    }
}