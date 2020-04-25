/*
 * SampleDao.kt 2020. 4. 24
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.database.sample

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.messenger.repository.model.Sample
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author MyeongKi
 */
@Dao
interface SampleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSample(sample: Sample?): Completable

    @Query("SELECT * FROM sample")
    fun getSample(): Single<List<Sample>>
}