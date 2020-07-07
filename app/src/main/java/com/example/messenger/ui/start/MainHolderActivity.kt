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
import com.example.messenger.app.SnackbarHelper
import com.example.messenger.base.BaseActivity
import com.example.messenger.databinding.ActivityMainHolderBinding
import com.example.messenger.event.ErrorEvent

/**
 * @author MyeongKi
 */

class MainHolderActivity : BaseActivity() {
    lateinit var binding: ActivityMainHolderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_holder)
        binding.helper = MainHolderBottomNavigationHelper(this)
        observeEvent()
    }

    private fun observeEvent() {
        disposables.add(
            ErrorEvent.errorMessageSubject.subscribe {
                SnackbarHelper.show(this, it)
            }
        )
    }
}