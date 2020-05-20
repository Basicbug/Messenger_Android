package com.example.messenger.database.message

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.messenger.MessengerApp
import com.example.messenger.repository.model.Message

/**
 * @author bsgreentea
 */

@Database(entities = [Message::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
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