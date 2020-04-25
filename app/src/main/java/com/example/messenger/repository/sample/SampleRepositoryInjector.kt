/*
 * SampleRepositoryInjector.kt 2020. 4. 25
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.sample

/**
 * @author MyeongKi
 */

object SampleRepositoryInjector {
    fun getSampleRepositoryImpl(): SampleRepositoryImpl {
        return SampleRepositoryImpl.getInstance()
    }
}