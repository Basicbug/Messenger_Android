/*
 * AppBarViewModel.kt 2020. 7. 27
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.common

import androidx.databinding.Bindable
import com.example.messenger.BR
import com.example.messenger.R
import com.example.messenger.base.BaseItemViewModel

/**
 * @author MyeongKi
 */

class AppBarViewModel :BaseItemViewModel(){
    @get:Bindable
    var title = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }
    override fun getLayoutRes(): Int = R.layout.common_app_bar
}