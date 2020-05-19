package com.example.messenger.repository.message

import com.example.messenger.repository.model.Message
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

/**
 * @author bsgreentea
 */
interface MessageRepository {
    fun insertMessageToLocal(msg: Message): Completable
    fun getMessageListFromLocal(roomID: Int): Single<List<Message>>
    fun getLatestFiftyMessages(roomID: Int, from: Int): Single<List<Message>>
}