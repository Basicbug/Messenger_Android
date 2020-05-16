package com.example.messenger.repository.message

import com.example.messenger.repository.model.Message
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import ua.naiksoftware.stomp.dto.StompMessage

/**
 * @author bsgreentea
 */
interface MessageRepository {
    fun insertMessageToLocal(msg: Message): Completable
    fun getMessageListFromLocal(roomID: Int): Single<List<Message>>
    fun subscribeChattingRoom(roomID: Int): Flowable<StompMessage>
    fun sendMessageToServer(msg: Message): Completable
}