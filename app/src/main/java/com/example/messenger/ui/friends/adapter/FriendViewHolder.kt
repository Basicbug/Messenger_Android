/*
 * FriendViewHolder.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.friends.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.databinding.ItemFriendBinding
import com.example.messenger.repository.model.user.UserInfo

/**
 * @author MyeongKi
 */

class FriendViewHolder(
    private val binding: ItemFriendBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val friendViewModel = FriendItemViewModel().apply {
        binding.friendItemViewModel = this
    }

    fun bind(friendInfo: UserInfo) {
        friendViewModel.name = friendInfo.name ?: ""
        friendViewModel.stateMessage = friendInfo.stateMessage ?: ""
    }
}