/*
 * SampleActivity.kt 2020. 4. 24
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.sample

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.messenger.R
import com.example.messenger.base.BaseActivity
import com.example.messenger.databinding.ActivitySampleBinding

/**
 * @author MyeongKi
 */

class SampleActivity : BaseActivity() {
    private lateinit var sampleViewModel: SampleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sampleViewModel =
            SampleViewModelInjector.provideSampleViewModelFactor().create(SampleViewModel::class.java)

        val binding: ActivitySampleBinding = DataBindingUtil.setContentView(this, R.layout.activity_sample)
        binding.lifecycleOwner = this
        binding.viewModel = sampleViewModel

    }
}