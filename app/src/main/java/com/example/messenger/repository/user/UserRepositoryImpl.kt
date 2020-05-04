/*
 * UserRepositoryImpl.kt 2020. 5. 4
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.user

import com.example.messenger.database.user.UserDatabase
import com.example.messenger.repository.model.user.FriendRelation
import com.example.messenger.repository.model.user.UserInfo
import io.reactivex.Single

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFriendRelationListFromServer(userId: String): Single<ArrayList<FriendRelation>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}