package com.example.messenger.ui.chattingroom

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.messenger.R
import com.example.messenger.base.BaseActivity
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

        chattingRoomViewModel =
            ChattingRoomViewModelInjector.provideSampleViewModelFactory("1")
                .create(ChattingRoomViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting_room)
        binding.chatRoomViewModel = chattingRoomViewModel

        binding.lifecycleOwner = this

        val messageAdapter = MessageAdapter()
        binding.recyclerview.adapter = messageAdapter

//        chattingRoomViewModel.loadMessageUseCase.loadMessages()
    }

    private fun subscribeMessageList(messageAdapter: MessageAdapter) {
        chattingRoomViewModel.messageList.observe(this, Observer {
            messageAdapter.submitList(it.toMutableList())
        })
    }
}