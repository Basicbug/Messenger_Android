package com.example.messenger.repository.message

import com.example.messenger.repository.model.Message
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author bsgreentea
 */
interface MessageRepository {
    fun insertMessageToLocal(msg: Message): Completable
    fun getMessageListFromLocal(roomID: Int): Single<List<Message>>
}