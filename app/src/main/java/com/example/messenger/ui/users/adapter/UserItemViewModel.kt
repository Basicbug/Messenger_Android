/*
 * FriendItemViewModel.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.users.adapter

import androidx.databinding.Bindable
import com.example.messenger.BR
import com.example.messenger.R
import com.example.messenger.base.BaseItemViewModel

/**
 * @author MyeongKi
 */

class UserItemViewModel : BaseItemViewModel() {
    @get:Bindable
    var name = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var stateMessage = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.stateMessage)
        }

    override fun getLayoutRes(): Int = R.layout.item_user
}