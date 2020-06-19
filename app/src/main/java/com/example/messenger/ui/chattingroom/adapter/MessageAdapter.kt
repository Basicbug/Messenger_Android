package com.example.messenger.ui.chattingroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.R
import com.example.messenger.repository.model.Message

/**
 * @author bsgreentea
 */
class MessageAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(
    MessageDiffCallback()
) {

    companion object {
        private const val RECEIVED = 1
        private const val SENT = 2
    }

    override fun getItemViewType(position: Int): Int {
        val message: Message = this.getItem(position)

//        if(message.senderName == ) return SENT
//        else return RECEIVED

        return SENT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == RECEIVED) {
            MessageReceivedViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_message_received, parent, false
                )
            )
        } else { // viewType == SENT
            MessageSentViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_message_sent, parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is MessageReceivedViewHolder) {
            holder.bind(getItem(position))
        } else if (holder is MessageSentViewHolder) {
            holder.bind(getItem(position))
        }
    }
}