/*
 * FriendsLoadUseCase.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.usecase

import com.example.messenger.base.BaseUseCase
import com.example.messenger.event.FriendEvent
import com.example.messenger.repository.model.user.FriendRelation
import com.example.messenger.repository.model.user.UserInfo
import com.example.messenger.repository.user.UserRepositoryImpl
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */

class LoadFriendsUseCase(
    private val userId: String,
    private val userRepository: UserRepositoryImpl,
    private val disposables: CompositeDisposable
) : BaseUseCase {
    override fun execute() {
        getFriendRelationListFromServer(userId)
    }

    private fun getFriendRelationListFromServer(userId: String) {
        disposables.add(
            userRepository.getFriendRelationListFromServer(userId)
                .doOnSuccess {
                    insertFriendRelationListToLocal(it)
                    for (item in it) {
                        item.id?.let {
                            friendUid:String->
                            getFriendInfoFromServer(friendUid)
                        }
                    }
                }
                .doOnError {
                    //TODO 스냅바로 실패 보여주기
                    getFriendRelationListFromLocal()
                }
                .subscribe()
        )
    }

    private fun getFriendRelationListFromLocal() {
        disposables.add(
            userRepository.getFriendRelationListFromLocal()
                .doOnSuccess {
                    for (item in it) {
                        item.id?.let {
                                friendUid:String->
                            getFriendInfoFromLocal(friendUid)
                        }
                    }
                }
                .subscribe()
        )
    }

    private fun getFriendInfoFromLocal(userId: String) {
        disposables.add(
            userRepository.getUserInfoFromLocal(userId)
                .doOnSuccess {
                    FriendEvent.addFriendToList(it)
                }
                .doOnError {
                    //TODO 스냅바로 실패
                }
                .subscribe()
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
                .doOnSuccess {
                    FriendEvent.addFriendToList(it)
                    insertFriendInfoToLocal(it)
                }
                .doOnError {
                    //TODO 스냅바로 실패
                    getFriendInfoFromLocal(userId)
                }
                .subscribe()
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