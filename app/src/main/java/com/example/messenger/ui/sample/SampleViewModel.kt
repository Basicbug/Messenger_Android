/*
 * SampleViewModel.kt 2020. 4. 24
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.messenger.repository.model.Sample
import com.example.messenger.repository.sample.SampleRepositoryImpl
import io.reactivex.disposables.CompositeDisposable


/**
 * @author MyeongKi
 */
class SampleViewModel(
    private val sampleRepositoryImpl: SampleRepositoryImpl
) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()

    val sampleResult = MutableLiveData<Sample>()
    val sampleResultLocal= MutableLiveData<Sample>()
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun loadSampleData() {
        disposables.add(
            sampleRepositoryImpl
                .getSampleFromServer()
                .doOnSuccess { sampleResult.value = it }
                .subscribe()
        )
    }

    fun loadSampleDataFromLocal() {
        disposables.add(
            sampleRepositoryImpl
                .getSampleFromLocal()
                .doOnSuccess { sampleResultLocal.value = it }
                .subscribe()
        )
    }

    fun saveSampleData() {
        disposables.add(
            sampleRepositoryImpl
                .saveSample(sampleResult.value)
                .subscribe()

        )
    }
}