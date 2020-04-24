/*
 * SampleDatabase.kt 2020. 4. 24
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.database.sample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.messenger.repository.model.Sample

/**
 * @author MyeongKi
 */

@Database(entities = [Sample::class], version = 1, exportSchema = false)
abstract class SampleDatabase : RoomDatabase() {
    abstract fun sampleDao(): SampleDao

    companion object {
        private const val DB_NAME = "smaple.db"
        @JvmStatic
        fun getDatabase(context: Context): SampleDatabase {
            return Room.databaseBuilder(context, SampleDatabase::class.java, DB_NAME).build()
        }
    }
}