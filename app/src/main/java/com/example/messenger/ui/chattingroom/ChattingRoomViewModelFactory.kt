package com.example.messenger.ui.chattingroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.messenger.repository.message.MessageRepositoryImpl

/**
 * @author bsgreentea
 */
class ChattingRoomViewModelFactory(
    val repository: MessageRepositoryImpl,
    val roomId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChattingRoomViewModel(repository, roomId) as T
    }
}