package com.example.messenger.ui.chattingroom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.messenger.constants.AppInfoConstants
import com.example.messenger.event.ChattingRoomEvent
import com.example.messenger.repository.message.MessageRepositoryImpl
import com.example.messenger.repository.model.Message
import com.example.messenger.type.MessageType
import com.example.messenger.usecase.LoadMessagesUseCase
import com.example.messenger.usecase.ReceiveMessageUseCase
import com.example.messenger.usecase.SendMessageUseCase
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

/**
 * @author bsgreentea
 */
class ChattingRoomViewModel(
    messageRepository: MessageRepositoryImpl,
    roomId: String
) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()

    val loadMessageUseCase = LoadMessagesUseCase(messageRepository, disposables)
    val messageList = MutableLiveData<MutableList<Message>>().apply {
        value = ArrayList()
    }

    val chatListViewModel = ChatListViewModel(loadMessageUseCase, roomId)

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
                "a5f4974e-bdbe-4f58-8d66-c7fd1ea4449e",
                "mk",
                "jw",
                MessageType.MESSAGE,
                "test",
                "time"
            )
        )

    }

    fun getText(): ObservableField<String> {
        return messageToSend
    }

    private fun subscribeEvent() {
        disposables.add(
            ChattingRoomEvent.addMessageToListSubject
                .subscribe {
                    messageList.value?.add(it)
                    messageList.postValue(messageList.value)
                }
        )

        disposables.add(
            ChattingRoomEvent.addMessagesToListSubject
                .debounce(AppInfoConstants.NO_DUPLICATION, TimeUnit.MILLISECONDS)
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