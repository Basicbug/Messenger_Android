/*
 * LoginActivity.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.login

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.messenger.R
import com.example.messenger.base.BaseActivity
import com.example.messenger.databinding.ActivityLoginBinding

/**
 * @author bsgreentea
 */

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.loginActionHelper = LoginActionHelper()
    }
}