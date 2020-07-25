/*
 * UserRepositoryImpl.kt 2020. 5. 4
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.user

import com.example.messenger.database.user.UserDatabase
import com.example.messenger.network.ApiHelper
import com.example.messenger.network.service.user.UserInfoService
import com.example.messenger.repository.model.user.UserInfo
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author MyeongKi
 */
class UserRepositoryImpl : UserInfoRepository {
    private val userInfoDao = UserDatabase.getDatabase().userInfoDao()


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: UserRepositoryImpl? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: UserRepositoryImpl().also { instance = it }
            }
    }

    override fun getUserInfoFromServer(userId: String): Single<UserInfo> {
        return ApiHelper
            .createApiByService(UserInfoService::class)
            .getUserInfo(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data }

    }

    override fun getLoginUserInfoFromServer(): Single<UserInfo> {
        return ApiHelper
            .createApiByService(UserInfoService::class)
            .getLoginUserInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data }

    }

    override fun getUserInfoFromLocal(userId: String): Single<UserInfo> {
        return userInfoDao
            .getUserInfo(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFriendsInfoFromLocal(): Single<List<UserInfo>> {
        return userInfoDao
            .getFriendsInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertUserInfoToLocal(item: UserInfo): Completable {
        return userInfoDao
            .insertUserInfo(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertUsersInfoToLocal(items: List<UserInfo>): Completable {
        return userInfoDao
            .insertUsersInfo(items)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFriendsFromServer(): Single<ArrayList<UserInfo>> {
        return ApiHelper
            .createApiByService(UserInfoService::class)
            .getFriendsInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.dataList }

    }

}