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

    @Query("SELECT * FROM messages WHERE room_id = :roomID ORDER BY message_time")
    fun getMessageList(roomID: Int): Single<List<Message>>
    //TODO sql 수정 필요
    @Query("SELECT * FROM messages WHERE room_id = :roomID ORDER BY message_time")
    fun getLatestFiftyMessages(roomID: Int, from: Int): Single<List<Message>>
}