/*
 * StartActivity.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.start

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.messenger.R
import com.example.messenger.base.BaseActivity
import com.example.messenger.databinding.ActivityStartBinding

/**
 * @author MyeongKi
 */

class StartActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityStartBinding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        binding.helper = StartBottomNavigationHelper(this)
    }
}