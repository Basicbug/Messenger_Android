/*
 * ChatRoomRepository.kt 2020. 5. 21
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.chat

import com.example.messenger.repository.model.chat.ChatRoom
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author MyeongKi
 */

interface ChatRoomRepository {
    fun getChatRoomListFromServer(userId: String): Single<ArrayList<ChatRoom>>
    fun insertChatRoomListToLocal(items: ArrayList<ChatRoom>): Completable
    fun getChatRoomListFromLocal(): Single<List<ChatRoom>>
    fun deleteChatRoomToLocal(item: ChatRoom): Completable
}