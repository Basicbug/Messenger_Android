package com.example.messenger.event

import com.example.messenger.repository.model.chat.Message
import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.subjects.PublishSubject

/**
 * @author bsgreentea
 */
object ChattingRoomEvent {
    val messageSubject = PublishSubject.create<Message>()
    fun invokeMessage(message: Message) {
        messageSubject.onNext(message)
    }

    val messageListSubject = PublishSubject.create<List<Message>>()
    fun invokeMessageList(messages: List<Message>) {
        messageListSubject.onNext(messages)
    }

    val notifySendMessageRelay: PublishRelay<String> = PublishRelay.create()
    fun notifySendMessage(motion: String) {
        notifySendMessageRelay.accept(motion)
    }
}