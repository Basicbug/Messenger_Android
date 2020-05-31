package com.example.messenger.ui.chattingroom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.messenger.event.ChattingRoomEvent
import com.example.messenger.repository.message.MessageRepositoryImpl
import com.example.messenger.repository.model.Message
import com.example.messenger.usecase.LoadMessagesUseCase
import io.reactivex.disposables.CompositeDisposable

/**
 * @author bsgreentea
 */
class ChattingRoomViewModel(
    messageRepository: MessageRepositoryImpl
) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()
    val loadMessageUseCase = LoadMessagesUseCase(messageRepository, disposables)
    val messageList = MutableLiveData<ArrayList<Message>>().apply {
        value = ArrayList()
    }

//    private val chatListViewModel = ChatListViewModel(loadMessageUseCase)

    private var messageToSend: ObservableField<String>

    init {
        subscribeEvent()
        messageToSend = ObservableField("")
    }

    fun sendMessageToServer() {

    }

    fun getText(): ObservableField<String> {
        return messageToSend
    }

    private fun subscribeEvent() {
        disposables.add(
            ChattingRoomEvent.addMessageToListSubject.subscribe {
                messageList.value?.add(it)
                messageList.postValue(messageList.value)
            }
        )
    }

    // TODO 메세지 로드하기???

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}