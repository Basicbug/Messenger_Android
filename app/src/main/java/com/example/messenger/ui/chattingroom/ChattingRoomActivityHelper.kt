package com.example.messenger.ui.chattingroom

import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.base.BaseHelper
import com.example.messenger.ui.chattingroom.adapter.MessageRecyclerViewListener
import com.example.messenger.usecase.LoadMessagesUseCase

/**
 * @author bsgreentea
 */
class ChattingRoomActivityHelper(
    private val loadMessagesUseCase: LoadMessagesUseCase,
    private val roomId: String
) : BaseHelper {

    override fun customizeRecyclerView(view: RecyclerView) {
        view.addOnScrollListener(
            MessageRecyclerViewListener(
                loadMessagesUseCase,
                roomId
            )
        )
    }

}