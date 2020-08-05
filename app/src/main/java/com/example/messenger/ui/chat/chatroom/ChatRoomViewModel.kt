package com.example.messenger.ui.chat.chatroom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.messenger.base.BaseViewModel
import com.example.messenger.event.ChatRoomEvent
import com.example.messenger.repository.chat.MessageRepositoryImpl
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
    @Suppress("UNUSED_PARAMETER") roomId: String
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
    var messageToSend: ObservableField<String>

    init {
        subscribeEvent()
        messageToSend = ObservableField("")
    }

    fun sendMessageToServer() {

        if (messageToSend.get().toString() == "") return
        sendMessageUseCase.sendMessage(
            Message(
                "1",
                "mk",
                "jw",
                "test",
                "time",
                MessageType.MESSAGE
            )
        )
        messageToSend.set("")
    }

    private fun subscribeEvent() {
        disposables.add(
            ChatRoomEvent.messageSubject
                .subscribe {
                    messageList.value?.add(it)
                    messageList.postValue(messageList.value)
                }
        )

        disposables.add(
            ChatRoomEvent.messageListSubject
                .debounce(NO_DUPLICATION_DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    messageList.value?.addAll(0, it)
                    messageList.postValue(messageList.value)
                }
        )
        receiveMessageUseCase.subscribeChattingRoom("1")
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}