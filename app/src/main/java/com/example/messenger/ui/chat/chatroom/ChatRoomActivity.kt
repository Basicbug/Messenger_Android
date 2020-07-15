package com.example.messenger.ui.chat.chatroom

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.messenger.R
import com.example.messenger.base.BaseSocketActivity
import com.example.messenger.databinding.ActivityChatRoomBinding
import com.example.messenger.repository.model.chat.Message
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

        for (i in 1..200) {
            val msg = Message(
                "1", i.toString(), "1", "sender", i.toString()
            )
            chatRoomViewModel.loadMessageUseCase.insertMessageToLocal(msg)
        }

        chatRoomViewModel.loadMessageUseCase.loadMessages("1", 0)
//        chattingRoomViewModel.loadMessageUseCase.loadMessages("a5f4974e-bdbe-4f58-8d66-c7fd1ea4449e", 0)
    }

    private fun subscribeMessageList(messageAdapter: MessageAdapter) {
        chatRoomViewModel.messageList.observe(this, Observer {
            messageAdapter.submitList(it.toMutableList())
        })
    }
}