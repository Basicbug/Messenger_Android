package com.example.messenger.ui.chattingroom

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.messenger.R
import com.example.messenger.base.BaseSocketActivity
import com.example.messenger.databinding.ActivityChattingRoomBinding
import com.example.messenger.ui.chattingroom.adapter.MessageAdapter

/**
 * @author bsgreentea
 */
class ChattingRoomActivity : BaseSocketActivity() {

    lateinit var binding: ActivityChattingRoomBinding
    private lateinit var chattingRoomViewModel: ChattingRoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting_room)

        chattingRoomViewModel =
            ChattingRoomViewModelInjector.provideSampleViewModelFactory("1")
                .create(ChattingRoomViewModel::class.java)

        binding.chatRoomViewModel = chattingRoomViewModel
        binding.lifecycleOwner = this

        binding.chatRoomHelper =
            ChattingRoomActivityHelper(
                chattingRoomViewModel.loadMessageUseCase,
//                "a5f4974e-bdbe-4f58-8d66-c7fd1ea4449e"
                "1"
            )

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