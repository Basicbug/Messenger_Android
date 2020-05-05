/*
 * UserRepositoryImpl.kt 2020. 5. 4
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.user

import com.example.messenger.database.user.UserDatabase
import com.example.messenger.network.ApiHelper
import com.example.messenger.network.service.SampleService
import com.example.messenger.network.service.user.FriendRelationService
import com.example.messenger.network.service.user.UserInfoService
import com.example.messenger.repository.model.user.FriendRelation
import com.example.messenger.repository.model.user.UserInfo
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author MyeongKi
 */
class UserRepositoryImpl : UserInfoRepository, FriendRelationRepository {
    private val userInfoDao = UserDatabase.getDatabase().userInfoDao()
    private val friendRelationDao = UserDatabase.getDatabase().friendRelationDao()


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

    override fun getUserInfoFromLocal(userId: String): Single<UserInfo> {
        return userInfoDao
            .getUserInfo(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertUserInfoToLocal(item: UserInfo): Completable {
        return userInfoDao
            .insertUserInfo(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFriendRelationListFromServer(userId: String): Single<ArrayList<FriendRelation>> {
        return ApiHelper
            .createApiByService(FriendRelationService::class)
            .getFriendRelationList(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.dataList }

    }

    override fun insertFriendRelationListToLocal(items: ArrayList<FriendRelation>): Completable {
        return friendRelationDao
            .insertFriendRelationList(items)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFriendRelationListFromLocal(): Single<List<FriendRelation>> {
        return friendRelationDao
            .getFriendRelationList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteFriendRelationToLocal(item: FriendRelation): Completable {
        return friendRelationDao
            .deleteFriendRelation(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}