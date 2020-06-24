package com.example.messenger.repository.message

import com.example.messenger.repository.model.chat.Message
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import ua.naiksoftware.stomp.dto.StompMessage

/**
 * @author bsgreentea
 */
interface MessageRepository {
    fun insertMessageToLocal(msg: Message): Completable

    fun getMessageListFromLocal(roomId: String): Single<List<Message>>
    fun getLatestFiftyMessages(roomId: String, from: Int): Single<List<Message>>
    fun subscribeChattingRoom(roomId: String): Flowable<StompMessage>
    fun sendMessageToSocketServer(msg: Message): Completable

}