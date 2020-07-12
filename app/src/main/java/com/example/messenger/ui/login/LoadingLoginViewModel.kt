/*
 * LoginLoadingViewModel.kt 2020. 6. 29
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.login

import androidx.databinding.Bindable
import com.example.messenger.BR
import com.example.messenger.R
import com.example.messenger.base.BaseItemViewModel

/**
 * @author MyeongKi
 */

class LoadingLoginViewModel : BaseItemViewModel() {
    @get:Bindable
    var loginPageVisible: Boolean = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.loginPageVisible)
        }

    override fun getLayoutRes(): Int = R.layout.activity_login
}