package com.example.messenger.event

import com.example.messenger.repository.model.Message
import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.subjects.PublishSubject

/**
 * @author bsgreentea
 */
object ChattingRoomEvent {
    val addMessageToListSubject = PublishSubject.create<Message>()
    fun addMessageToList(message: Message) {
        addMessageToListSubject.onNext(message)
    }

    val addMessagesToListSubject = PublishSubject.create<List<Message>>()
    fun addMessagesToList(messages: List<Message>) {
        addMessagesToListSubject.onNext(messages)
    }

    val notifySendMessageRelay: PublishRelay<String> = PublishRelay.create()
    fun notifySendMessage(motion: String) {
        notifySendMessageRelay.accept(motion)
    }
}