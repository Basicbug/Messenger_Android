/*
 * LoadFriendsEvent.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.event

import com.example.messenger.manager.NaverLoginManager
import com.example.messenger.repository.model.user.UserInfo
import io.reactivex.subjects.PublishSubject

/**
 * @author MyeongKi
 */

object UserEvent {
    val friendsInfoSubject = PublishSubject.create<List<UserInfo>>()
    val loginUserInfoSubject = PublishSubject.create<UserInfo>()

    fun invokeFriendsInfo(friendsInfo: List<UserInfo>) {
        friendsInfoSubject.onNext(friendsInfo)
    }

    fun invokeLoginUserInfo(loginUserInfo: UserInfo) {
        NaverLoginManager.loginUserInfo = loginUserInfo
        loginUserInfoSubject.onNext(loginUserInfo)
    }
}