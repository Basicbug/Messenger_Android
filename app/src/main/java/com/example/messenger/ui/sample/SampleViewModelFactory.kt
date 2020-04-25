/*
 * SampleViewModelFactory.kt 2020. 4. 25
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.messenger.repository.sample.SampleRepositoryImpl

/**
 * @author MyeongKi
 */
class SampleViewModelFactory(
    private val repository: SampleRepositoryImpl
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SampleViewModel(repository) as T
    }
}