/*
 * RxActionBus.kt 2020. 8. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.util.bus

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.Observable

/**
 * @author MyeongKi
 */

object RxActionBus {
    fun post(event : RxAction){
        RxBus.post(event)
    }
    fun <T : RxAction>toObservable(eventType:Class<T>):Observable<T>{
        return RxBus.toObservable(eventType)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(AndroidSchedulers.mainThread())
    }
}