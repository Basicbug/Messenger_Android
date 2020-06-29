package com.example.messenger.ui.chattingroom

import android.view.View
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.base.BaseHelper
import com.example.messenger.repository.model.Message
import com.example.messenger.type.MessageType
import com.example.messenger.ui.chattingroom.adapter.MessageRecyclerViewListener
import com.example.messenger.usecase.LoadMessagesUseCase
import com.example.messenger.usecase.ReceiveMessageUseCase
import com.example.messenger.usecase.SendMessageUseCase

/**
 * @author bsgreentea
 */
class ChattingRoomActivityHelper(
    private val receiveMessageUseCase: ReceiveMessageUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val loadMessagesUseCase: LoadMessagesUseCase,
    private val roomId: String
) : BaseHelper {

    var messageToSend: ObservableField<String> = ObservableField("")

    override fun customizePropertiesView(view: View) {
        (view as RecyclerView).addOnScrollListener(
            MessageRecyclerViewListener(
                loadMessagesUseCase,
                roomId
            )
        )
    }

    fun sendMessageToServer(view: View) {

        val message = messageToSend.get().toString()

        if (message != "") {
            testReceivedMessage(message, view)
        }
//        sendMessageUseCase.sendMessage(
//            Message(
////                "a5f4974e-bdbe-4f58-8d66-c7fd1ea4449e",
//                "1",
//                "mk",
//                "jw",
//                MessageType.MESSAGE,
//                "test",
//                "time"
//            )
//        )
        messageToSend.set("")
    }

    var cnt = 203
    private fun testReceivedMessage(text: String, view: View) {

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
}