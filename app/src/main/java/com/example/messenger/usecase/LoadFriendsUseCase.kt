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
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */

class LoadFriendsUseCase(
    private val userRepository: UserRepositoryImpl,
    private val disposables: CompositeDisposable
) {
    fun loadFriends(userId: String) {
        getFriendsFromServer(userId)
    }

    private fun getFriendsFromServer(userId: String) {
        disposables.add(
            userRepository.getFriendsFromServer(userId)
                .subscribe(
                    { friends ->
                        friends.map {
                            it.isFriend = true
                        }
                        //확인 필
                        UserEvent.invokeFriendsInfo(friends)
                        insertFriendsInfoToLocal(friends)
                    },
                    { error ->
                        ErrorEvent.invokeErrorMessage(R.string.load_fail_from_server)
                        Log.d(this.javaClass.simpleName, error.message?:"")
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
                        //TODO 스냅바로 실패
                    }
                )
        )
    }

    private fun insertFriendsInfoToLocal(friends:List<UserInfo>) {
        disposables.add(
            userRepository.insertUsersInfoToLocal(friends)
                .subscribe()
        )
    }

}