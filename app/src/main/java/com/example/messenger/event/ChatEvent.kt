/*
 * ChatEvent.kt 2020. 5. 30
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.event

import com.example.messenger.repository.model.chat.Message
import com.example.messenger.repository.model.chat.ChatRoom
import io.reactivex.subjects.PublishSubject

/**
 * @author MyeongKi
 */

object ChatEvent {

    val chatRoomAndLastMessageSubject = PublishSubject.create<Pair<ChatRoom, Message>>()

    fun invokeChatRoomAndLastMessage(chatRoom: Pair<ChatRoom, Message>) {
        chatRoomAndLastMessageSubject.onNext(chatRoom)
    }

    val messagePushedSubject = PublishSubject.create<Message>()

    fun messagePushed(message: Message) {
        messagePushedSubject.onNext(message)
    }
}