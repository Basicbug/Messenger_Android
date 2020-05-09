package com.example.messenger.database.message

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.messenger.repository.model.Message
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author bsgreentea
 */
@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: Message): Completable

    @Query("SELECT * FROM messages WHERE roomID = :roomID ORDER BY messageTime")
    fun getMessageList(roomID: Int): Single<List<Message>>
}