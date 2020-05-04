/*
 * UserDao.kt 2020. 5. 4
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.messenger.repository.model.user.UserInfo
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author MyeongKi
 */

@Dao
interface UserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfo(userInfo: UserInfo?): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfoList(userInfoList: ArrayList<UserInfo>?): Completable

    @Query("SELECT * FROM UserInfo")
    fun getUserInfoList(): Single<List<UserInfo>>
}