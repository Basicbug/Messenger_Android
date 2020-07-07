/*
 * ErrorEvent.kt 2020. 7. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.event

import androidx.annotation.StringRes
import com.example.messenger.app.AppResources
import io.reactivex.subjects.PublishSubject


/**
 * @author MyeongKi
 */
object ErrorEvent {
    val errorMessageSubject = PublishSubject.create<String>()
    fun invokeErrorMessage(@StringRes errorMessageResId:Int){
        invokeErrorMessage(AppResources.getStringResId(errorMessageResId))
    }
    fun invokeErrorMessage(errorMessage: String) {
        errorMessageSubject.onNext(errorMessage)
    }

}

