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
    val addFriendToListSubject = PublishSubject.create<UserInfo>()
    fun addFriendToList(friendInfo: UserInfo) {
        addFriendToListSubject.onNext(friendInfo)
    }
}