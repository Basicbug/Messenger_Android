package com.example.messenger.base

import com.example.messenger.repository.model.Message

/**
 * @author bsgreentea
 */
interface BaseMessageViewHolder {
    fun bind(message: Message)
}