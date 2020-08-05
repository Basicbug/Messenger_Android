/*
 * LoadLoginUserUseCase.kt 2020. 7. 18
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.usecase

import com.example.messenger.event.UserEvent
import com.example.messenger.repository.user.UserRepositoryImpl
import com.example.messenger.util.errorLog
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */
class LoadLoginUserUseCase(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val disposables: CompositeDisposable
) {
    fun loadLoginUserInfo() {
        disposables.add(
            userRepositoryImpl.getLoginUserInfoFromServer()
                .subscribe(
                    { result ->
                        result.data?.let { loginUserInfo ->
                            UserEvent.invokeLoginUserInfo(loginUserInfo)
                        }

                    },
                    { error ->
                        errorLog(this, error)
                    }
                )
        )
    }
}