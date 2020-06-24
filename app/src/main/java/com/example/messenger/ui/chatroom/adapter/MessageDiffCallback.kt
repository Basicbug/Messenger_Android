package com.example.messenger.ui.chatroom.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.messenger.repository.model.chat.Message

/**
 * @author bsgreentea
 */
class MessageDiffCallback : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.messageTime == newItem.messageTime
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return (oldItem.toString() == newItem.toString())
    }
}