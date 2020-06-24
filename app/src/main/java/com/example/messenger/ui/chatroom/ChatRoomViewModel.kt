package com.example.messenger.ui.chatroom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.messenger.base.BaseViewModel
import com.example.messenger.event.ChattingRoomEvent
import com.example.messenger.repository.message.MessageRepositoryImpl
import com.example.messenger.repository.model.chat.Message
import com.example.messenger.type.MessageType
import com.example.messenger.usecase.LoadMessagesUseCase
import com.example.messenger.usecase.ReceiveMessageUseCase
import com.example.messenger.usecase.SendMessageUseCase
import java.util.concurrent.TimeUnit

/**
 * @author bsgreentea
 */
class ChatRoomViewModel(
    messageRepository: MessageRepositoryImpl,
    roomId: String
) : BaseViewModel() {

    companion object {
        private const val NO_DUPLICATION_DEBOUNCE_TIME: Long = 25
    }

    val loadMessageUseCase = LoadMessagesUseCase(messageRepository, disposables)
    val messageList = MutableLiveData<MutableList<Message>>().apply {
        value = ArrayList()
    }

    private val receiveMessageUseCase = ReceiveMessageUseCase(messageRepository, disposables)
    private val sendMessageUseCase = SendMessageUseCase(messageRepository, disposables)
    private var messageToSend: ObservableField<String>

    init {
        subscribeEvent()
        messageToSend = ObservableField("")
    }

    fun sendMessageToServer() {
        sendMessageUseCase.sendMessage(
            Message(

            )
        )

    }

    fun getText(): ObservableField<String> {
        return messageToSend
    }

    private fun subscribeEvent() {
        disposables.add(
            ChattingRoomEvent.messageSubject
                .subscribe {
                    messageList.value?.add(it)
                    messageList.postValue(messageList.value)
                }
        )

        disposables.add(
            ChattingRoomEvent.messageListSubject
                .debounce(NO_DUPLICATION_DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    messageList.value?.addAll(0, it)
                    messageList.postValue(messageList.value)
                }
        )
//        receiveMessageUseCase.subscribeChattingRoom("a5f4974e-bdbe-4f58-8d66-c7fd1ea4449e")
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}