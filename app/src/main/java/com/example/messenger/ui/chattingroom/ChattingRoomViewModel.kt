package com.example.messenger.ui.chattingroom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.messenger.repository.message.MessageRepositoryImpl
import com.example.messenger.repository.model.Message
import com.example.messenger.type.MessageType
import com.example.messenger.usecase.ReceiveMessageUseCase
import com.example.messenger.usecase.SendMessageUseCase
import io.reactivex.disposables.CompositeDisposable

/**
 * @author bsgreentea
 */
class ChattingRoomViewModel(
    val repository: MessageRepositoryImpl
) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()
        //val loadChattingRoomUseCase = ChattingRoomUseCase()
    val messageList = MutableLiveData<ArrayList<Message>>().apply {
        value = ArrayList()
    }
    private val receiveMessageUseCase = ReceiveMessageUseCase(repository, disposables)
    private val sendMessageUseCase = SendMessageUseCase(repository, disposables)
    private var messageToSend: ObservableField<String>

    init {
        subscribeEvent()
        messageToSend = ObservableField("")
    }

    fun onClickSend() {
        sendMessageUseCase.sendMessage(Message("6957eeaf-ccc8-4ace-be8a-cde3656c2061", "mk", "jw", MessageType.MESSAGE, "test", "time"))

    }

    fun getText(): ObservableField<String>{
        return messageToSend
    }

    private fun subscribeEvent() {
        receiveMessageUseCase.subscribeChattingRoom("6957eeaf-ccc8-4ace-be8a-cde3656c2061")
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}