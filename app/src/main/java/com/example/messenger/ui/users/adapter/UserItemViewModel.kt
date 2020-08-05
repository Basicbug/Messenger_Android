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
import com.example.messenger.common.event.DefaultItemActionEvent
import com.example.messenger.common.event.DefaultItemActionEventFactory
import com.example.messenger.repository.model.user.UserInfo
import com.example.messenger.type.ItemEvent

/**
 * @author MyeongKi
 */

class UserItemViewModel : BaseItemViewModel() {
    var userInfo: UserInfo? = null
        set(value) {
            value?.let {
                field = value
                name = it.name ?: ""
                stateMessage = it.status ?: ""
            }

        }

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


    override fun onClickItemView() {
        super.onClickItemView()
        userInfo?.let {
            invokeEvent(DefaultItemActionEventFactory.createEvent(it, ItemEvent.CLICK))
        }

    }

    override fun getLayoutRes(): Int = R.layout.item_user
}