package com.example.messenger.ui.chattingroom

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.messenger.R
import com.example.messenger.base.BaseActivity
import com.example.messenger.databinding.ActivityChattingRoomBinding
import com.example.messenger.repository.model.Message
import com.example.messenger.ui.chattingroom.adapter.MessageAdapter

/**
 * @author bsgreentea
 */
class ChattingRoomActivity : BaseActivity() {

    lateinit var binding: ActivityChattingRoomBinding
    private lateinit var chattingRoomViewModel: ChattingRoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        chattingRoomViewModel =
            ChattingRoomViewModelInjector.provideSampleViewModelFactory()
                .create(ChattingRoomViewModel::class.java)

        binding.chatRoomViewModel = chattingRoomViewModel
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting_room)
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