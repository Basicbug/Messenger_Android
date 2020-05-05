/*
 * FriendDiffCallback.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.friends.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.messenger.repository.model.user.UserInfo

/**
 * @author MyeongKi
 */

class FriendDiffCallback : DiffUtil.ItemCallback<UserInfo>() {
    override fun areItemsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
        return (oldItem.toString() == newItem.toString())
    }

}