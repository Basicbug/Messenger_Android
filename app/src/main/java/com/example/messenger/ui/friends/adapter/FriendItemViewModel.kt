/*
 * FriendItemViewModel.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.friends.adapter

import com.example.messenger.repository.model.user.UserInfo

/**
 * @author MyeongKi
 */

class FriendItemViewModel(friendInfo: UserInfo) {
    val name = friendInfo.name
    val stateMessage = friendInfo.stateMessage
}