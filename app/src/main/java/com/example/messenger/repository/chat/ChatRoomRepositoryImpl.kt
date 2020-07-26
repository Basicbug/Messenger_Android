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
import com.example.messenger.repository.model.ApiData
import com.example.messenger.repository.model.ApiDataList
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

    override fun getChatRoomListFromServer(userId: String): Single<ApiDataList<ChatRoom>> {
        return ApiHelper
            .createApiByService(ChatRoomService::class)
            .getChatRoomList(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getChatRoomDetailFromServer(roomId: String): Single<ApiData<ChatRoom>> {
        return ApiHelper
            .createApiByService(ChatRoomService::class)
            .getChatRoomDetail(roomId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertChatRoomToLocal(item: ChatRoom): Completable {
        return chatRoomDao
            .insertChatRoom(item)
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

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: ChatRoomRepositoryImpl? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: ChatRoomRepositoryImpl().also { instance = it }
            }
    }
}