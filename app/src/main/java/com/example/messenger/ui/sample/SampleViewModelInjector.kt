/*
 * SampleViewModelInjector.kt 2020. 4. 25
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.sample

import android.content.Context
import com.example.messenger.repository.sample.SampleRepositoryInjector

/**
 * @author MyeongKi
 */

object SampleViewModelInjector {
    fun provideSampleViewModelFactor(
        context: Context
    ): SampleViewModelFactory {
        val repository = SampleRepositoryInjector.getSampleRepositoryImpl(context)
        return SampleViewModelFactory(repository)
    }
}