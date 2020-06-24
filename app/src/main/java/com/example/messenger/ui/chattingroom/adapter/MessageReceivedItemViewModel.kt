package com.example.messenger.ui.chattingroom.adapter

import com.example.messenger.repository.model.Message

/**
 * @author bsgreentea
 */
class MessageReceivedItemViewModel(message: Message) {
    val senderName = message.senderName
    val messageContent = message.messageContent
    val messageTime = message.messageTime
}