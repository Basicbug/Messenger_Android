package com.example.messenger.ui.chat.chatroom

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.base.BaseHelper
import com.example.messenger.event.ChatRoomEvent
import com.example.messenger.ui.chat.chatroom.adapter.MessageRecyclerViewListener
import com.example.messenger.usecase.LoadMessagesUseCase

/**
 * @author bsgreentea
 */
class ChatRoomActivityHelper(
    private val loadMessagesUseCase: LoadMessagesUseCase,
    private val roomId: String
) : BaseHelper {

    override fun customizePropertiesView(view: View) {
        (view as RecyclerView).addOnScrollListener(
            MessageRecyclerViewListener(
                loadMessagesUseCase,
                roomId
            )
        )

        ChatRoomEvent.notifySendMessageRelay
            .subscribe {

                if (it == "sender") {
                    view.adapter?.let { adapter ->
                        (adapter.itemCount - 1).takeIf { itemCount ->
                            itemCount > 0
                        }?.let { bottom ->
                            view.scrollToPosition(bottom)
                        }
                    }
                }

            }
    }
}