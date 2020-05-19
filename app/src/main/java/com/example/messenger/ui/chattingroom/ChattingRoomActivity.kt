package com.example.messenger.ui.chattingroom

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.messenger.R
import com.example.messenger.base.BaseActivity
import com.example.messenger.databinding.ActivityChattingRoomBinding

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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting_room)
        binding.viewModel = chattingRoomViewModel
    }
}