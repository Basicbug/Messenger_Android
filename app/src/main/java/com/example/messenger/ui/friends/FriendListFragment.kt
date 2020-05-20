/*
 * FriendListFragment.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.example.messenger.R
import com.example.messenger.base.BaseFragment
import com.example.messenger.databinding.FragmentFriendListBinding
import com.example.messenger.repository.model.user.UserInfo
import com.example.messenger.ui.friends.adapter.FriendAdapter


/**
 * @author MyeongKi
 */

class FriendListFragment : BaseFragment() {
    private lateinit var binding: FragmentFriendListBinding
    private lateinit var friendListViewModel: FriendListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        friendListViewModel =
            FriendViewModelInjector.provideFriendViewModelFactory().create(FriendListViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friend_list, container, false)
        binding.friendListViewModel = friendListViewModel
        binding.lifecycleOwner = this
        val friendAdapter = FriendAdapter()
        binding.friends.adapter = friendAdapter
        subscribeFriendInfoList(friendAdapter)
        friendListViewModel.loadFriendsUseCase.loadFriends("ChoMk")
        return binding.root
    }

    private fun subscribeFriendInfoList(adapter: FriendAdapter) {
        friendListViewModel.friendList.observe(viewLifecycleOwner) { result: ArrayList<UserInfo> ->
            adapter.submitList(result.toMutableList())
        }
    }
}

//TODO 어뎁터를 그냥 Fragment에서 갖고있으면 좋을 듯 viewmodel, adpater, helper