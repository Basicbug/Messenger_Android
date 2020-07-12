/*
 * ChatRoomItemViewModel.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.chat.chatroomlist.adapter

import com.example.messenger.repository.model.chat.ChatRoom

/**
 * @author MyeongKi
 */

class ChatRoomItemViewModel(chatRoom: ChatRoom) {
    val participants = chatRoom.name
    //TODO  수정 필요
}