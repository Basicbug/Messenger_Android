package com.example.messenger.ui.chatroom.adapter

import com.example.messenger.repository.model.chat.Message

/**
 * @author bsgreentea
 */
class MessageReceivedItemViewModel(message: Message) {
    val senderName = message.senderUid
    val messageContent = message.message
    val messageTime = message.timestamp
}