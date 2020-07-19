/*
 * UserRepository.kt 2020. 5. 4
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.user

import com.example.messenger.repository.model.user.UserInfo
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author MyeongKi
 */

interface UserInfoRepository {
    fun getUserInfoFromServer(userId: String): Single<UserInfo>
    fun getLoginUserInfoFromServer(): Single<UserInfo>
    fun getFriendsFromServer(userId: String): Single<ArrayList<UserInfo>>
    fun getUserInfoFromLocal(userId: String): Single<UserInfo>
    fun getFriendsInfoFromLocal(): Single<List<UserInfo>>
    fun insertUserInfoToLocal(item: UserInfo): Completable
    fun insertUsersInfoToLocal(items: List<UserInfo>): Completable
}