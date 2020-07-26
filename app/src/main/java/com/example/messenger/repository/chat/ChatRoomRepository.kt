/*
 * ChatRoomRepository.kt 2020. 5. 21
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.chat

import com.example.messenger.repository.model.ApiData
import com.example.messenger.repository.model.ApiDataList
import com.example.messenger.repository.model.chat.ChatRoom
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author MyeongKi
 */

interface ChatRoomRepository {
    fun getChatRoomListFromServer(userId: String): Single<ApiDataList<ChatRoom>>
    fun getChatRoomDetailFromServer(roomId: String): Single<ApiData<ChatRoom>>
    fun insertChatRoomToLocal(item: ChatRoom): Completable
    fun getChatRoomListFromLocal(): Single<List<ChatRoom>>
    fun deleteChatRoomToLocal(item: ChatRoom): Completable
}