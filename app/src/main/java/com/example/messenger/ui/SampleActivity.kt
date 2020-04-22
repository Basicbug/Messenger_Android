/*
 * SampleActivity.kt 2020. 4. 22
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui

import android.os.Bundle
import com.example.messenger.R
import com.example.messenger.base.BaseActivity

/**
 * @author MyeongKi
 */

class SampleActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}