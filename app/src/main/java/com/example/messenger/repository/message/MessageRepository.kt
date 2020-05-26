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
    fun getMessageListFromLocal(roomId: String): Single<List<Message>>
    fun getLatestFiftyMessages(roomId: String, from: Int): Single<List<Message>>
}