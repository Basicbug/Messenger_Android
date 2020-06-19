package com.example.messenger.event

import com.example.messenger.repository.model.Message
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
    fun addMessagesToList(messages: List<Message>){
        addMessagesToListSubject.onNext(messages)
    }
}