package com.example.messenger.ui.chattingroom

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.base.BaseHelper
import com.example.messenger.event.ChattingRoomEvent
import com.example.messenger.ui.chattingroom.adapter.MessageRecyclerViewListener
import com.example.messenger.usecase.LoadMessagesUseCase

/**
 * @author bsgreentea
 */
class ChattingRoomActivityHelper(
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

        ChattingRoomEvent.notifySendMessageRelay
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