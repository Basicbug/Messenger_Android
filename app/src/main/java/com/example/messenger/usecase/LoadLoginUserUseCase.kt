/*
 * LoadLoginUserUseCase.kt 2020. 7. 18
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.usecase

import android.util.Log
import com.example.messenger.event.UserEvent
import com.example.messenger.repository.user.UserRepositoryImpl
import com.example.messenger.tools.errorLog
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */
class LoadLoginUserUseCase (
    private val userRepositoryImpl: UserRepositoryImpl,
    private val disposables: CompositeDisposable
){
    fun loadLoginUserInfo() {
        disposables.add(
            userRepositoryImpl.getLoginUserInfoFromServer()
                .subscribe(
                    { userInfo ->
                        UserEvent.invokeLoginUserInfo(userInfo)
                    },
                    { error ->
                        errorLog(this, error)
                    }
                )
        )
    }
}