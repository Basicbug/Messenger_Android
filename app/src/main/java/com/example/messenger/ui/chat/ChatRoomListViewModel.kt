/*
 * ChatRoomListViewModel.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.messenger.event.ChatEvent
import com.example.messenger.repository.chat.ChatRoomRepositoryImpl
import com.example.messenger.repository.model.chat.ChatRoom
import com.example.messenger.usecase.LoadChatRoomListUseCase
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */
class ChatRoomListViewModel(
    chatRoomRepository: ChatRoomRepositoryImpl
) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()
    val loadChatRoomUseCase = LoadChatRoomListUseCase("ChoMk", chatRoomRepository, disposables)
    val chatRoomList = MutableLiveData<ArrayList<ChatRoom>>().apply {
        value = ArrayList()
    }

    init {
        subscribeEvent()
    }

    private fun subscribeEvent(){
        disposables.add(
            ChatEvent.addChatRoomToListSubject.subscribe {
                chatRoomList.value?.add(it)
                chatRoomList.postValue(chatRoomList.value)
            }
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}