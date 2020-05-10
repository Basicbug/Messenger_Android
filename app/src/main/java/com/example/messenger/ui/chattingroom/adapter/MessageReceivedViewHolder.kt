package com.example.messenger.ui.chattingroom.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.databinding.ItemMessageReceivedBinding
import com.example.messenger.repository.model.Message

/**
 * @author bsgreentea
 */

class MessageReceivedViewHolder(
    val binding: ItemMessageReceivedBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(message: Message) {
        binding.receivedItemViewModel = MessageReceivedItemViewModel(message)
    }
}