package com.example.messenger.ui.chattingroom

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.base.BaseViewModel
import com.example.messenger.event.ChattingRoomEvent
import com.example.messenger.repository.message.MessageRepositoryImpl
import com.example.messenger.repository.model.Message
import com.example.messenger.type.MessageType
import com.example.messenger.usecase.LoadMessagesUseCase
import com.example.messenger.usecase.ReceiveMessageUseCase
import com.example.messenger.usecase.SendMessageUseCase
import java.util.concurrent.TimeUnit

/**
 * @author bsgreentea
 */
class ChattingRoomViewModel(
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

    val receiveMessageUseCase = ReceiveMessageUseCase(messageRepository, disposables)
    val sendMessageUseCase = SendMessageUseCase(messageRepository, disposables)
    private var messageToSend: ObservableField<String>

    init {
        subscribeEvent()
        messageToSend = ObservableField("")
    }

    fun sendMessageToServer(view: View) {
        testReceivedMessage(view, messageToSend.get().toString())
        sendMessageUseCase.sendMessage(
            Message(
                "a5f4974e-bdbe-4f58-8d66-c7fd1ea4449e",
//                "1",
                "mk",
                "jw",
                MessageType.MESSAGE,
                "test",
                "time"
            )
        )
        messageToSend.set("")
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
                .debounce(NO_DUPLICATION_DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    messageList.value?.addAll(0, it)
                    messageList.postValue(messageList.value)
                }
        )
//        receiveMessageUseCase.subscribeChattingRoom("a5f4974e-bdbe-4f58-8d66-c7fd1ea4449e")
//        receiveMessageUseCase.subscribeChattingRoom("1")
    }

    var cnt = 201
    private fun testReceivedMessage(view: View, text: String) {

        val msg = Message(
            "1", "sender", "receiver", MessageType.MESSAGE,
            text, cnt++.toString()
        )

        receiveMessageUseCase.testReceiveMessage(msg)
        scrollToBottom(view)
    }

    private fun scrollToBottom(view: View) {

        (view as RecyclerView).adapter?.let { adapter ->
            (adapter.itemCount - 1).takeIf { itemCount ->
                itemCount > 0
            }?.let { bottom ->
                view.scrollToPosition(bottom)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}