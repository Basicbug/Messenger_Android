/*
 * ChatRoomRepositoryImpl.kt 2020. 5. 21
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.chat

import com.example.messenger.database.chat.ChatDatabase
import com.example.messenger.network.ApiHelper
import com.example.messenger.network.service.chat.ChatRoomService
import com.example.messenger.repository.model.chat.ChatRoom
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author MyeongKi
 */
class ChatRoomRepositoryImpl : ChatRoomRepository {
    private val chatRoomDao = ChatDatabase.getDatabase().chatRoomDao()

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
        return ApiHelper
            .createApiByService(ChatRoomService::class)
            .getChatRoomList(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.dataList }

    }

    override fun insertChatRoomListToLocal(items: ArrayList<ChatRoom>): Completable {
        return chatRoomDao
            .insertChatRoomList(items)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getChatRoomListFromLocal(): Single<List<ChatRoom>> {
        return chatRoomDao
            .getChatRoomList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteChatRoomToLocal(item: ChatRoom): Completable {
        return chatRoomDao
            .deleteChatRoom(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}