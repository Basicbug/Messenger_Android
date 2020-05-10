package com.example.messenger.ui.chattingroom.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.databinding.ItemMessageSentBinding
import com.example.messenger.repository.model.Message

/**
 * @author bsgreentea
 */
class MessageSentViewHolder(
    val binding: ItemMessageSentBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(message: Message) {
        binding.sentItemViewModel = MessageSentItemViewModel(message)
    }
}