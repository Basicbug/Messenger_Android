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

    @Query("SELECT * FROM messages WHERE room_id =:roomId ORDER BY message_time DESC LIMIT 50 OFFSET :from")
    fun getLatestFiftyMessages(roomId: String, from: Int): Single<List<Message>>

    @Query("SELECT * FROM messages WHERE room_id = :roomId ORDER BY message_time")
    fun getMessageList(roomId: String): Single<List<Message>>

}