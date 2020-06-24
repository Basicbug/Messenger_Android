/*
 * ChatRoomRepositoryInjector.kt 2020. 5. 21
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.chat

/**
 * @author MyeongKi
 */
object ChatRoomRepositoryInjector {
    fun getChatRoomRepositoryImpl(): ChatRoomRepositoryImpl {
        return ChatRoomRepositoryImpl.getInstance()
    }
}