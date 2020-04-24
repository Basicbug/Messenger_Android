/*
 * SampleRepositoryInjector.kt 2020. 4. 25
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.sample

import android.content.Context

/**
 * @author MyeongKi
 */

object SampleRepositoryInjector {
    fun getSampleRepositoryImpl(context: Context):SampleRepositoryImpl{
        return SampleRepositoryImpl.getInstance(context)
    }
}