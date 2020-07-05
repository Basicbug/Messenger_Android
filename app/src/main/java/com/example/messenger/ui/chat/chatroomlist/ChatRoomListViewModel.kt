/*
 * ChatRoomListViewModel.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.chat.chatroomlist

import android.util.ArrayMap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.messenger.event.ChatEvent
import com.example.messenger.repository.chat.ChatRoomRepositoryImpl
import com.example.messenger.repository.chat.MessageRepositoryImpl
import com.example.messenger.repository.model.chat.ChatRoom
import com.example.messenger.repository.model.chat.Message
import com.example.messenger.usecase.LoadChatRoomListUseCase
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */
class ChatRoomListViewModel(
    chatRoomRepository: ChatRoomRepositoryImpl,
    messageRepository: MessageRepositoryImpl
) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()
    val loadChatRoomUseCase = LoadChatRoomListUseCase("ChoMk", chatRoomRepository, messageRepository, disposables)
    val chatRoomList = MutableLiveData<MutableList<ChatRoom>>()
    private val chatRoomTable = ArrayMap<String, ChatRoom>()
    private val lastMessageTable = ArrayMap<String, Message>()

    init {
        subscribeEvent()
    }

    private fun subscribeEvent() {
        disposables.add(
            ChatEvent.chatRoomAndLastMessageSubject.subscribe {
                val chatRoom = it.first
                val lastMessage = it.second
                chatRoomTable[chatRoom.roomId] = chatRoom
                lastMessageTable[lastMessage.id] = lastMessage
                //TODO 시간순으로 정렬 로직 넣자...
                chatRoomList.postValue(chatRoomTable.values
                    .toMutableList()
                    .also { list ->
                        list.sortBy { chatRoom -> lastMessageTable[chatRoom.lastMessageId]?.timestamp }
                    }
                )

            }
        )
        disposables.add(
            ChatEvent.messagePushedSubject.subscribe {
                //TODO ReceiveMessageUseCase를 통하여 푸시온 메시지 처리(저장)
                //TODO UpdateChatRoomUseCase(메시지방 갱신 후 이벤트 발생하여 위 에서 리스트 정렬해주)
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}