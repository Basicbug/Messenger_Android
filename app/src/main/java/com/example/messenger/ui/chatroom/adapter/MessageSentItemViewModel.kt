package com.example.messenger.ui.chatroom.adapter

import com.example.messenger.repository.model.chat.Message

/**
 * @author bsgreentea
 */
class MessageSentItemViewModel(message: Message) {
    val messageContent = message.message
    val messageTime = message.timestamp
}