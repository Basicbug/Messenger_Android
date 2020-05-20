package com.example.messenger.repository.message

import com.example.messenger.database.message.MessageDatabase
import com.example.messenger.repository.model.Message
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author bsgreentea
 */
class MessageRepositoryImpl : MessageRepository {

    private val messageDao = MessageDatabase.getDatabase().messageDao()

    override fun getMessageListFromLocal(roomId: Int): Single<List<Message>> {
        return messageDao
            .getMessageList(roomId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getLatestFiftyMessages(roomId: Int, from: Int): Single<List<Message>> {
        return messageDao
            .getLatestFiftyMessages(roomId, from)
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
                instance ?: MessageRepositoryImpl().also { instance = it }
            }
    }
}