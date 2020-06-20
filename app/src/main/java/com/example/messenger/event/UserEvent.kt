/*
 * LoadFriendsEvent.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.event

import com.example.messenger.repository.model.user.UserInfo
import io.reactivex.subjects.PublishSubject

/**
 * @author MyeongKi
 */


object FriendEvent {
    val friendInfoSubject = PublishSubject.create<UserInfo>()
    fun invokeFriendInfo(friendInfo: UserInfo) {
        friendInfoSubject.onNext(friendInfo)
    }
}