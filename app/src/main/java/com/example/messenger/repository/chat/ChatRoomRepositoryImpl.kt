/*
 * ChatRoomRepositoryImpl.kt 2020. 5. 21
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
class ChatRoomRepositoryImpl : ChatRoomRepository {
    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: ChatRoomRepositoryImpl? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: ChatRoomRepositoryImpl().also { instance = it }
            }
    }

    override fun getChatRoomListFromServer(userId: String): Single<ArrayList<ChatRoom>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertChatRoomListToLocal(items: ArrayList<ChatRoom>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChatRoomListFromLocal(): Single<List<ChatRoom>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteChatRoomToLocal(item: ChatRoom): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}