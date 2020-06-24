/*
 * ChatRoomListFragment.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.chatroomlist

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
import com.example.messenger.ui.chatroomlist.adapter.ChatRoomAdapter

/**
 * @author MyeongKi
 */

class ChatRoomListFragment : BaseFragment() {
    private lateinit var binding: FragmentChatRoomListBinding
    private lateinit var chatRoomListViewModel: ChatRoomListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        chatRoomListViewModel =
            ChatRoomListViewModelInjector.provideChatRoomViewModelFactory().create(ChatRoomListViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_room_list, container, false)
        binding.viewModel = chatRoomListViewModel
        binding.lifecycleOwner = this
        val adapter = ChatRoomAdapter()
        binding.chatRoomList.adapter = adapter
        subscribeFriendInfoList(adapter)
        chatRoomListViewModel.loadChatRoomUseCase.loadChatRoomList()
        return binding.root
    }

    private fun subscribeFriendInfoList(adapter: ChatRoomAdapter) {
        chatRoomListViewModel.chatRoomList.observe(viewLifecycleOwner) { result: MutableList<ChatRoom> ->
            adapter.submitList(result)
        }
    }
}