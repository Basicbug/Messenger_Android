package com.example.messenger.ui.chat.chatroom.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.messenger.repository.model.chat.Message

/**
 * @author bsgreentea
 */
class MessageDiffCallback : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.timestamp == newItem.timestamp
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return (oldItem.toString() == newItem.toString())
    }
}