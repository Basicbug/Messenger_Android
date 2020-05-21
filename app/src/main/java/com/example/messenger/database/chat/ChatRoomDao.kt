/*
 * ChatRoomDao.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.database.chat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.messenger.repository.model.chat.ChatRoom
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author MyeongKi
 */
@Dao
interface ChatRoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChatRoom(chatRoom: ChatRoom?): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChatRoomList(chatRoomList: ArrayList<ChatRoom>?): Completable

    @Query("SELECT * FROM ChatRoom")
    fun getChatRoomList(): Single<List<ChatRoom>>

    @Query("SELECT * FROM ChatRoom WHERE roomId LIKE :roomId")
    fun getChatRoom(roomId: String): Single<ChatRoom>

}