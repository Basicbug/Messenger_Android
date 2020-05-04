/*
 * UserRepositoryImpl.kt 2020. 5. 4
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.user

import com.example.messenger.network.ApiHelper
import com.example.messenger.network.service.UserService
import com.example.messenger.repository.model.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author MyeongKi
 */
class UserRepositoryImpl : UserRepository {

    override fun getFriends(userId: String): Single<ArrayList<User>> {
        return ApiHelper
            .createApiByService(UserService::class)
            .getFriends(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }//TODO 여기서 로컬 로직 추가...

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: UserRepositoryImpl? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: UserRepositoryImpl().also { instance = it }
            }
    }

}