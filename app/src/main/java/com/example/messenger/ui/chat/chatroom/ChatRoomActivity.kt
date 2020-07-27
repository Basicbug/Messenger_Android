package com.example.messenger.ui.chat.chatroom

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.R
import com.example.messenger.base.BaseSocketActivity
import com.example.messenger.databinding.ActivityChatRoomBinding
import com.example.messenger.repository.model.chat.Message
import com.example.messenger.type.ChatRoomListStateType
import com.example.messenger.ui.chat.chatroom.adapter.MessageAdapter

/**
 * @author bsgreentea
 */
class ChatRoomActivity : BaseSocketActivity() {

    lateinit var binding: ActivityChatRoomBinding
    private lateinit var chatRoomViewModel: ChatRoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val messageAdapter = MessageAdapter()
        messageAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {

                when (messageAdapter.state) {
                    ChatRoomListStateType.RAW -> {
                        messageAdapter.state = ChatRoomListStateType.INITIATED
                    }
                    ChatRoomListStateType.INITIATED -> {
                        messageAdapter.state = ChatRoomListStateType.DONE
                        binding.recyclerview.layoutManager?.scrollToPosition(messageAdapter.itemCount - 1)
                    }
                    ChatRoomListStateType.DONE -> {
                        if (itemCount == 1) {
                            if (positionStart != 0 || messageAdapter.itemCount <= 0)
                                binding.recyclerview.layoutManager?.scrollToPosition(messageAdapter.itemCount - 1)
                        }
                    }
                }
            }
        })

        chatRoomViewModel =
            ChatRoomViewModelInjector.provideSampleViewModelFactory("1")
                .create(ChatRoomViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_room)

        binding.apply {
            lifecycleOwner = this@ChatRoomActivity
            chatRoomViewModel = this@ChatRoomActivity.chatRoomViewModel
            recyclerview.adapter = messageAdapter
            chatRoomHelper = ChatRoomActivityHelper(
                this@ChatRoomActivity.chatRoomViewModel.loadMessageUseCase,
                "1"
            )
        }

        subscribeMessageList(messageAdapter)

        chatRoomViewModel.loadMessageUseCase.loadMessages("1", 0)
    }

    private fun subscribeMessageList(messageAdapter: MessageAdapter) {
        chatRoomViewModel.messageList.observe(this, Observer {
            messageAdapter.submitList(it.toMutableList())
        })
    }
}