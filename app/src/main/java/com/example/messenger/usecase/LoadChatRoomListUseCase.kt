/*
 * LoadChatRoomListUseCase.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.usecase

import com.example.messenger.event.ChatEvent
import com.example.messenger.repository.chat.ChatRoomRepositoryImpl
import com.example.messenger.repository.chat.MessageRepositoryImpl
import com.example.messenger.repository.model.chat.ChatRoom
import com.example.messenger.repository.model.chat.Message
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */

class LoadChatRoomListUseCase(
    private val userId: String,
    private val chatRoomRepositoryImpl: ChatRoomRepositoryImpl,
    private val messageRepositoryImpl: MessageRepositoryImpl,
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
                    getLastMessageFromServer(it)
                }
                .doOnError {
                    //TODO 스냅바로 실패 보여주기
                    getChatRoomListFromLocal()
                }
                .subscribe()
        )
    }

    private fun getLastMessageFromServer(chatRoom: ChatRoom) {
        disposables.add(
            messageRepositoryImpl.getMessageFromServer(chatRoom.lastMessageId, chatRoom.lastMessageId)
                .doOnSuccess {
                    insertLastMessageToLocal(it)
                    ChatEvent.invokeChatRoomAndLastMessage(Pair(chatRoom, it))
                }
                .doOnError {
                    getLastMessageFromLocal(chatRoom)
                }
                .subscribe()
        )
    }

    private fun getChatRoomListFromLocal() {
        disposables.add(
            chatRoomRepositoryImpl.getChatRoomListFromLocal()
                .doOnSuccess {
                    it.forEach { chatRoom ->
                        getLastMessageFromLocal(chatRoom)//이미 서버와의 에러가 떨어진 상태에서 로컬로 찌르는게 나을 듯
                    }
                }
                .doOnError {
                    //에러메시지
                }
                .subscribe()
        )
    }

    private fun getLastMessageFromLocal(chatRoom: ChatRoom) {
        disposables.add(
            messageRepositoryImpl.getMessageFromLocal(chatRoom.lastMessageId, chatRoom.lastMessageId)
                .doOnSuccess {
                    ChatEvent.invokeChatRoomAndLastMessage(Pair(chatRoom, it))
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

    private fun insertLastMessageToLocal(item: Message) {
        disposables.add(
            messageRepositoryImpl.insertMessageToLocal(item)
                .subscribe()
        )
    }

}