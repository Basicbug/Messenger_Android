package com.example.messenger.ui.chat.chatroom

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
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

        chattingRoomViewModel.loadMessageUseCase.loadMessages("1", 0)
    }

    private fun subscribeMessageList(messageAdapter: MessageAdapter) {
        chattingRoomViewModel.messageList.observe(this, Observer {
            messageAdapter.submitList(it.toMutableList())
        })
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        hideKeyboard()
        return super.dispatchTouchEvent(ev)
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        view?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}