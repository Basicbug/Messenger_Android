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
import com.example.messenger.repository.model.user.FriendRelation
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
        getFriendRelationListFromServer(userId)
    }

    private fun getFriendRelationListFromServer(userId: String) {
        disposables.add(
            userRepository.getFriendRelationListFromServer(userId)
                .subscribe(
                    { friendRelations ->
                        insertFriendRelationListToLocal(friendRelations)
                        for (item in friendRelations) {
                            getFriendInfoFromServer(item.id)
                        }
                    },
                    { error ->
                        ErrorEvent.invokeErrorMessage(R.string.load_fail_from_server)
                        Log.d(this.javaClass.simpleName, error.message)
                        getFriendRelationListFromLocal()
                    }
                )
        )
    }

    private fun getFriendRelationListFromLocal() {
        disposables.add(
            userRepository.getFriendRelationListFromLocal()
                .subscribe(
                    { friendRelations ->
                        for (item in friendRelations) {
                            getFriendInfoFromLocal(item.id)
                        }
                    },
                    {}
                )
        )
    }

    private fun getFriendInfoFromLocal(userId: String) {
        disposables.add(
            userRepository.getUserInfoFromLocal(userId)

                .subscribe(
                    { userInfo ->
                        UserEvent.invokeFriendInfo(userInfo)
                    },
                    { error ->
                        //TODO 스냅바로 실패
                    }
                )
        )
    }

    private fun insertFriendRelationListToLocal(items: ArrayList<FriendRelation>) {
        disposables.add(
            userRepository.insertFriendRelationListToLocal(items)
                .subscribe()
        )
    }

    private fun getFriendInfoFromServer(userId: String) {
        disposables.add(
            userRepository.getUserInfoFromServer(userId)
                .subscribe(
                    { userInfo ->
                        UserEvent.invokeFriendInfo(userInfo)
                        insertFriendInfoToLocal(userInfo)
                    },
                    { error ->
                        ErrorEvent.invokeErrorMessage(R.string.load_fail_from_server)
                        getFriendInfoFromLocal(userId)
                    }
                )
        )
    }

    private fun insertFriendInfoToLocal(item: UserInfo) {
        disposables.add(
            userRepository.insertUserInfoToLocal(item)
                .subscribe()
        )
    }

}

//TODO 매 userInfo 별로 이벤트를 발생하기 때문에 recyclerview notify 최적화 필요
//TODO 뷰를 갱신하는 경우마다 api를 찌르기 때문에 이에 대한 최적화가 필요