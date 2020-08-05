/*
 * LoadChatRoomListUseCase.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.usecase

import com.example.messenger.R
import com.example.messenger.event.ChatEvent
import com.example.messenger.event.ErrorEvent
import com.example.messenger.repository.chat.ChatRoomRepositoryImpl
import com.example.messenger.repository.chat.MessageRepositoryImpl
import com.example.messenger.repository.model.chat.ChatRoom
import com.example.messenger.repository.model.chat.Message
import com.example.messenger.util.errorLog
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
                .subscribe(
                    { result ->
                        result.dataList?.let { chatRooms ->
                            chatRooms.forEach { chatRoom ->
                                getChatRoomDetailFromServer(chatRoom.roomId)
                            }
                        }
                    },
                    { error ->
                        ErrorEvent.invokeErrorMessage(R.string.load_fail_from_server)
                        getChatRoomListFromLocal()
                        errorLog(this, error)
                    }
                )
        )
    }

    private fun getChatRoomDetailFromServer(roomId: String) {
        disposables.add(
            chatRoomRepositoryImpl.getChatRoomDetailFromServer(roomId)
                .subscribe(
                    { result ->
                        result.data?.let { chatRoom ->
                            insertChatRoomToLocal(chatRoom)
                            getLastMessageFromServer(chatRoom)
                        }

                    },
                    { error ->
                        ErrorEvent.invokeErrorMessage(R.string.load_fail_from_server)
                        getChatRoomListFromLocal()
                        errorLog(this, error)
                    }
                )
        )
    }

    private fun getLastMessageFromServer(chatRoom: ChatRoom) {
        disposables.add(
            messageRepositoryImpl.getMessageFromServer(chatRoom.lastMessageId, chatRoom.lastMessageId)
                .subscribe(
                    { result ->
                        result.data?.let { message ->
                            insertLastMessageToLocal(message)
                            ChatEvent.invokeChatRoomAndLastMessage(Pair(chatRoom, message))
                        }
                    },
                    { error ->
                        ErrorEvent.invokeErrorMessage(R.string.load_fail_from_server)
                        getLastMessageFromLocal(chatRoom)
                        errorLog(this, error)
                    }
                )
        )
    }

    private fun getChatRoomListFromLocal() {
        disposables.add(
            chatRoomRepositoryImpl.getChatRoomListFromLocal()
                .subscribe(
                    { chatRooms ->
                        chatRooms.forEach { chatRoom ->
                            getLastMessageFromLocal(chatRoom)//이미 서버와의 에러가 떨어진 상태에서 로컬로 찌르는게 나을 듯
                        }
                    },
                    { error ->
                        errorLog(this, error)
                    }
                )
        )
    }

    private fun getLastMessageFromLocal(chatRoom: ChatRoom) {
        disposables.add(
            messageRepositoryImpl.getMessageFromLocal(chatRoom.lastMessageId, chatRoom.lastMessageId)
                .subscribe(
                    { message ->
                        ChatEvent.invokeChatRoomAndLastMessage(Pair(chatRoom, message))
                    },
                    { error ->
                        errorLog(this, error)
                    }
                )
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