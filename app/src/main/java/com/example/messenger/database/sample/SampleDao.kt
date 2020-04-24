/*
 * SampleDao.kt 2020. 4. 24
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.database.sample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.messenger.repository.model.Sample

/**
 * @author MyeongKi
 */
@Dao
interface SampleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSample(sample: Sample)

    @Query("SELECT * FROM sample")
    fun getSample(): LiveData<List<Sample>>
}