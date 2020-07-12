package com.example.messenger.ui.chat.chatroom.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.base.BaseMessageViewHolder
import com.example.messenger.databinding.ItemMessageSentBinding
import com.example.messenger.repository.model.chat.Message

/**
 * @author bsgreentea
 */
class MessageSentViewHolder(
    private val binding: ItemMessageSentBinding
) : RecyclerView.ViewHolder(binding.root), BaseMessageViewHolder {
    override fun bind(message: Message) {
        binding.sentItemViewModel = MessageSentItemViewModel(message)
    }
}