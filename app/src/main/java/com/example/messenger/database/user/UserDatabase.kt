/*
 * UserDatabase.kt 2020. 5. 4
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.database.user

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.messenger.app.AppResources
import com.example.messenger.repository.model.user.UserInfo

/**
 * @author MyeongKi
 */

@Database(entities = [UserInfo::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao

    companion object {
        private const val DB_NAME = "user.db"
        @JvmStatic
        fun getDatabase(): UserDatabase {
            return Room.databaseBuilder(AppResources.getContext(), UserDatabase::class.java, DB_NAME).build()
        }
    }
}