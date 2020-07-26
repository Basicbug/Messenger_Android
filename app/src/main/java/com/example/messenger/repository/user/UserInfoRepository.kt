/*
 * UserRepository.kt 2020. 5. 4
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.user

import com.example.messenger.repository.model.ApiData
import com.example.messenger.repository.model.ApiDataList
import com.example.messenger.repository.model.user.UserInfo
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author MyeongKi
 */

interface UserInfoRepository {
    fun getUserInfoFromServer(userId: String): Single<ApiData<UserInfo>>
    fun getLoginUserInfoFromServer(): Single<ApiData<UserInfo>>
    fun getFriendsFromServer(): Single<ApiDataList<UserInfo>>
    fun getUserInfoFromLocal(userId: String): Single<UserInfo>
    fun getFriendsInfoFromLocal(): Single<List<UserInfo>>
    fun insertUserInfoToLocal(item: UserInfo): Completable
    fun insertUsersInfoToLocal(items: List<UserInfo>): Completable
}