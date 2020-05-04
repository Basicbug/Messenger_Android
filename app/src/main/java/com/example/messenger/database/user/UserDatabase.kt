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
import com.example.messenger.MessengerApp
import com.example.messenger.repository.model.user.FriendRelation
import com.example.messenger.repository.model.user.UserInfo

/**
 * @author MyeongKi
 */

@Database(entities = [UserInfo::class, FriendRelation::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
    abstract fun friendRelationDao(): FriendRelationDao

    companion object {
        private const val DB_NAME = "user.db"
        @JvmStatic
        fun getDatabase(): UserDatabase {
            return Room.databaseBuilder(MessengerApp.applicationContext(), UserDatabase::class.java, DB_NAME).build()
        }
    }
}