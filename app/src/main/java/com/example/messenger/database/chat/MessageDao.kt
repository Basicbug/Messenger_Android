package com.example.messenger.database.chat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.messenger.repository.model.chat.ChatRoom
import com.example.messenger.repository.model.chat.Message
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author bsgreentea
 */
@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: Message): Completable

    //    @Query("SELECT * FROM messages WHERE room_id =:roomId ORDER BY message_time DESC LIMIT 50 OFFSET :from")
    // for the test
    @Query("SELECT * FROM messages WHERE room_id =:roomId ORDER BY cast(message_time as unsigned) DESC LIMIT 50 OFFSET :from")
    fun getLatestFiftyMessages(roomId: String, from: Int): Single<List<Message>>

    @Query("SELECT * FROM messages WHERE room_id = :roomId ORDER BY message_time")
    fun getMessageList(roomId: String): Single<List<Message>>

    @Query("SELECT * FROM messages WHERE room_id LIKE :roomId AND id LIKE :messageId")
    fun getMessage(messageId: String, roomId: String): Single<Message>

}