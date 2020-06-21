/*
 * LoadChatRoomListUseCase.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.usecase

import com.example.messenger.event.ChatEvent
import com.example.messenger.repository.chat.ChatRoomRepositoryImpl
import com.example.messenger.repository.model.chat.ChatRoom
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */

class LoadChatRoomListUseCase(
    private val userId: String,
    private val chatRoomRepositoryImpl: ChatRoomRepositoryImpl,
    private val disposables: CompositeDisposable
) {
    fun loadChatRoomList() {
        getChatRoomListFromServer(userId)
    }

    private fun getChatRoomListFromServer(userId: String) {
        disposables.add(
            chatRoomRepositoryImpl.getChatRoomListFromServer(userId)
                .doOnSuccess {
                    it.forEach { chatRoom ->
                        getChatRoomDetailFromServer(chatRoom.roomId)
                    }
                }
                .doOnError {
                    //TODO 스냅바로 실패 보여주기
                    getChatRoomListFromLocal()
                }
                .subscribe()
        )
    }

    private fun getChatRoomDetailFromServer(roomId: String) {
        disposables.add(
            chatRoomRepositoryImpl.getChatRoomDetailFromServer(roomId)
                .doOnSuccess {
                    insertChatRoomToLocal(it)
                    ChatEvent.addChatRoomToList(it)
                }
                .doOnError {
                    //TODO 스냅바로 실패 보여주기
                    getChatRoomListFromLocal()
                }
                .subscribe()
        )
    }


    private fun getChatRoomListFromLocal() {
        disposables.add(
            chatRoomRepositoryImpl.getChatRoomListFromLocal()
                .doOnSuccess {
                    it.forEach { chatRoom ->
                        ChatEvent.addChatRoomToList(chatRoom)
                    }
                }
                .subscribe()
        )
    }

    private fun insertChatRoomToLocal(item: ChatRoom) {
        disposables.add(
            chatRoomRepositoryImpl.insertChatRoomToLocal(item)
                .subscribe()
        )
    }
}