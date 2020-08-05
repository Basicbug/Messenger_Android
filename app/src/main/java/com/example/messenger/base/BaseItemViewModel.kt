/*
 * BaseItemViewModel.kt 2020. 6. 7
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.base

import androidx.databinding.BaseObservable
import com.example.messenger.common.BindableLayout
import com.example.messenger.util.bus.ItemEventRelayComponent
import com.example.messenger.util.bus.RxAction
import com.jakewharton.rxrelay2.Relay

/**
 * @author MyeongKi
 */

abstract class BaseItemViewModel : BaseObservable(), BindableLayout, ItemEventRelayComponent {
    override var itemEventRelay: Relay<RxAction>? = null
        set(value) {
            if (field == null) {
                field = value
            }
        }

    open fun onClickItemView() = Unit
    open fun onLongClickItemView() = Unit



    protected fun invokeEvent(rxAction: RxAction):Boolean{
        itemEventRelay?.let {
            it.accept(rxAction)
            return true
        }
        return false
    }
}