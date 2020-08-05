package com.example.messenger.event

import com.example.messenger.repository.model.chat.Message
import io.reactivex.subjects.PublishSubject

/**
 * @author bsgreentea
 */
object ChatRoomEvent {
    val messageSubject = PublishSubject.create<Message>()
    fun invokeMessage(message: Message) {
        messageSubject.onNext(message)
    }

    val messageListSubject = PublishSubject.create<List<Message>>()
    fun invokeMessageList(messages: List<Message>) {
        messageListSubject.onNext(messages)
    }
}