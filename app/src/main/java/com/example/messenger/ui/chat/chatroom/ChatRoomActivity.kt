package com.example.messenger.ui.chat.chatroom

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.messenger.R
import com.example.messenger.base.BaseSocketActivity
import com.example.messenger.databinding.ActivityChatRoomBinding
import com.example.messenger.ui.chat.chatroom.adapter.MessageAdapter

/**
 * @author bsgreentea
 */
class ChatRoomActivity : BaseSocketActivity() {

    lateinit var binding: ActivityChatRoomBinding
    private lateinit var chattingRoomViewModel: ChatRoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_room)

        chattingRoomViewModel =
            ChatRoomViewModelInjector.provideSampleViewModelFactory("1")
                .create(ChatRoomViewModel::class.java)

        binding.chatRoomViewModel = chattingRoomViewModel
        binding.lifecycleOwner = this

        binding.chatRoomHelper =
            ChatRoomActivityHelper(chattingRoomViewModel.loadMessageUseCase, "1")
        val messageAdapter = MessageAdapter()
        binding.recyclerview.adapter = messageAdapter

        subscribeMessageList(messageAdapter)

//        for (i in 1..200) {
//            val msg = Message(
//                "1", "sender", "receiver", MessageType.MESSAGE,
//                "test_msg", i.toString()
//            )
//            chattingRoomViewModel.loadMessageUseCase.insertMessageToLocal(msg)
//        }

        chattingRoomViewModel.loadMessageUseCase.loadMessages("1", 0)
//        chattingRoomViewModel.loadMessageUseCase.loadMessages("a5f4974e-bdbe-4f58-8d66-c7fd1ea4449e", 0)
    }

    private fun subscribeMessageList(messageAdapter: MessageAdapter) {
        chattingRoomViewModel.messageList.observe(this, Observer {
            messageAdapter.submitList(it.toMutableList())
        })
    }
}