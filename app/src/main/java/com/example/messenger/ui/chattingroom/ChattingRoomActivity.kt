package com.example.messenger.ui.chattingroom

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.messenger.R
import com.example.messenger.base.BaseActivity
import com.example.messenger.base.BaseSocketActivity
import com.example.messenger.databinding.ActivityChattingRoomBinding
import com.example.messenger.repository.model.Message
import com.example.messenger.type.MessageType
import com.example.messenger.ui.chattingroom.adapter.MessageAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

        val messageAdapter = MessageAdapter()
        binding.recyclerview.adapter = messageAdapter

        subscribeMessageList(messageAdapter)

        for(i in 1..200) {
            val msg = Message(
                "1", "sender", "receiver", MessageType.MESSAGE,
                "test_msg", i.toString()
            )
            chattingRoomViewModel.loadMessageUseCase.insertMessageToLocal(msg)
        }

        chattingRoomViewModel.loadMessageUseCase.loadMessages("1",0)
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