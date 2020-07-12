/*
 * MessageRepositoryImpl.kt 2020. 6. 28
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.chat

import com.example.messenger.database.chat.MessageDatabase
import com.example.messenger.network.ApiHelper
import com.example.messenger.network.SocketHelper
import com.example.messenger.network.service.chat.MessageService
import com.example.messenger.repository.model.chat.Message
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ua.naiksoftware.stomp.dto.StompMessage

/**
 * @author bsgreentea
 */
class MessageRepositoryImpl : MessageRepository {

    private val messageDao = MessageDatabase.getDatabase().messageDao()

    override fun getMessageListFromLocal(roomId: String): Single<List<Message>> {
        return messageDao
            .getMessageList(roomId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getMessageFromServer(messageId: String, roomId: String): Single<Message> {
        return ApiHelper
            .createApiByService(MessageService::class)
            .getMessage(messageId, roomId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data }
    }

    override fun getMessageFromLocal(messageId: String, roomId: String): Single<Message> {
        return messageDao
            .getMessage(messageId, roomId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getLatestFiftyMessages(roomId: String, from: Int): Single<List<Message>> {
        return messageDao
            .getLatestFiftyMessages(roomId, from)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun subscribeChattingRoom(roomId: String): Flowable<StompMessage> {
        return SocketHelper
            .createChattingRoomStream(roomId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    override fun sendMessageToSocketServer(msg: Message): Completable {
        return SocketHelper
            .createMessageSendStream(msg)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertMessageToLocal(msg: Message): Completable {
        return messageDao
            .insertMessage(msg)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {

        @Volatile
        private var instance: MessageRepositoryImpl? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: MessageRepositoryImpl()
                        .also { instance = it }
            }
    }
}