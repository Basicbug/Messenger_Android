/*
 * FriendsLoadUseCase.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.usecase

import android.util.Log
import com.example.messenger.R
import com.example.messenger.event.ErrorEvent
import com.example.messenger.event.UserEvent
import com.example.messenger.repository.model.user.UserInfo
import com.example.messenger.repository.user.UserRepositoryImpl
import com.example.messenger.tools.errorLog
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */

class LoadFriendsUseCase(
    private val userRepository: UserRepositoryImpl,
    private val disposables: CompositeDisposable
) {
    fun loadFriends() {
        getFriendsFromServer()
    }

    private fun getFriendsFromServer() {
        disposables.add(
            userRepository.getFriendsFromServer()
                .subscribe(
                    { result ->
                        result.dataList?.let { friends ->
                            friends.forEach { friend ->
                                friend.isFriend = true
                            }
                            UserEvent.invokeFriendsInfo(friends)
                            insertFriendsInfoToLocal(friends)
                        }
                    },
                    { error ->
                        ErrorEvent.invokeErrorMessage(R.string.load_fail_from_server)
                        errorLog(this, error)
                        getFriendsInfoFromLocal()
                    }
                )
        )
    }

    private fun getFriendsInfoFromLocal() {
        disposables.add(
            userRepository.getFriendsInfoFromLocal()
                .subscribe(
                    { userInfo ->
                        UserEvent.invokeFriendsInfo(userInfo as ArrayList<UserInfo>)
                    },
                    { error ->
                        errorLog(this, error)
                    }
                )
        )
    }


    private fun insertFriendsInfoToLocal(friends: List<UserInfo>) {
        disposables.add(
            userRepository.insertUsersInfoToLocal(friends)
                .subscribe()
        )
    }

}