/*
 * FriendViewHolder.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.users.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.databinding.ItemUserBinding
import com.example.messenger.repository.model.user.UserInfo

/**
 * @author MyeongKi
 */

class UserViewHolder(
    private val binding: ItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val userItemViewModel = UserItemViewModel().apply {
        binding.userItemViewModel = this
    }

    fun bind(userInfo: UserInfo) {
        userItemViewModel.name = userInfo.name ?: ""
        userItemViewModel.stateMessage = userInfo.status ?: ""
    }
}