/*
 * ChatRoomListFragment.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.chat.chatroomlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.example.messenger.R
import com.example.messenger.base.BaseFragment
import com.example.messenger.databinding.FragmentChatRoomListBinding
import com.example.messenger.repository.model.chat.ChatRoom
import com.example.messenger.ui.chat.chatroomlist.adapter.ChatRoomAdapter

/**
 * @author MyeongKi
 */

class ChatRoomListFragment : BaseFragment() {
    private lateinit var binding: FragmentChatRoomListBinding
    private lateinit var chatRoomListViewModel: ChatRoomListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_room_list, container, false)
        binding.lifecycleOwner = this
        injectViewModel()
        injectAdapter()
        executeUseCase()
        return binding.root
    }

    private fun initViewModel() {
        chatRoomListViewModel =
            ChatRoomListViewModelInjector.provideChatRoomViewModelFactory().create(ChatRoomListViewModel::class.java)
    }

    private fun injectViewModel() {
        initViewModel()
        binding.apply {
            viewModel = chatRoomListViewModel
        }
    }

    private fun injectAdapter() {
        binding.apply {
            chatRoomList.adapter = ChatRoomAdapter().also {
                subscribeFriendInfoList(it)
            }
        }
    }

    private fun subscribeFriendInfoList(adapter: ChatRoomAdapter) {
        chatRoomListViewModel.chatRoomList.observe(viewLifecycleOwner) { result: MutableList<ChatRoom> ->
            adapter.submitList(result)
        }
    }

    private fun executeUseCase() {
        chatRoomListViewModel.loadChatRoomUseCase.loadChatRoomList()

    }
}