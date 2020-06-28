/*
 * MessageDatabase.kt 2020. 6. 28
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.database.chat

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.messenger.MessengerApp
import com.example.messenger.repository.model.chat.Message

/**
 * @author bsgreentea
 */

@Database(entities = [Message::class], version = 1, exportSchema = false)
abstract class MessageDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao

    companion object {
        private const val DB_NAME = "messages.db"

        @JvmStatic
        fun getDatabase(): MessageDatabase {
            return Room.databaseBuilder(
                MessengerApp.applicationContext(),
                MessageDatabase::class.java,
                DB_NAME
            ).build()
        }
    }
}