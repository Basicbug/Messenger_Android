/*
 * LoadChatRoomListUseCase.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.usecase

import com.example.messenger.repository.chat.ChatRoomRepositoryImpl
import com.example.messenger.repository.user.UserRepositoryImpl
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */

class LoadChatRoomListUseCase(
    private val userId: String,
    private val chatRoomRepositoryImpl: ChatRoomRepositoryImpl,
    private val disposables: CompositeDisposable
) {
    fun loadChatRoomList(){

    }
}