/*
 * ChatDatabase.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.database.chat

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.messenger.MessengerApp

/**
 * @author MyeongKi
 */

@Database(entities = [ChatRoomDao::class], version = 1, exportSchema = false)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatRoomDao(): ChatRoomDao

    companion object {
        private const val DB_NAME = "chat_room.db"
        @JvmStatic
        fun getDatabase(): ChatDatabase {
            return Room.databaseBuilder(MessengerApp.applicationContext(), ChatDatabase::class.java, DB_NAME).build()
        }
    }
}