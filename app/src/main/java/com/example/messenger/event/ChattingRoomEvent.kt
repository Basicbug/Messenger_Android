package com.example.messenger.event

import com.example.messenger.repository.model.Message
import io.reactivex.subjects.PublishSubject

/**
 * @author bsgreentea
 */
object ChattingRoomEvent {
    private val addMessageToListSubject = PublishSubject.create<Message>()
    fun addMessageToList(message: Message) {
        addMessageToListSubject.onNext(message)
    }
}