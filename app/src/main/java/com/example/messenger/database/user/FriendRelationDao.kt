/*
 * FriendDao.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.messenger.repository.model.user.FriendRelation
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author MyeongKi
 */

@Dao
interface FriendRelationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFriendRelation(friendRelation: FriendRelation?): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFriendRelationList(friendRelationList: ArrayList<FriendRelation>?): Completable

    @Query("SELECT * FROM friendRelation")
    fun getFriendRelationList(): Single<List<FriendRelation>>
}