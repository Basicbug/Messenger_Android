package com.example.messenger.ui.chattingroom.adapter

import com.example.messenger.repository.model.Message

/**
 * @author bsgreentea
 */
class MessageSentItemViewModel(message: Message) {
    val messageContent = message.messageContent
    val messageTime = message.messageTime
}