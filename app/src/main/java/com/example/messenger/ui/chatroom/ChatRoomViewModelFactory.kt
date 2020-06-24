package com.example.messenger.ui.chatroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.messenger.repository.message.MessageRepositoryImpl

/**
 * @author bsgreentea
 */
class ChatRoomViewModelFactory(
    val repository: MessageRepositoryImpl,
    val roomId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatRoomViewModel(repository, roomId) as T
    }
}