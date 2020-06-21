package com.example.messenger.ui.chattingroom

import android.view.View
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

    override fun customizePropertiesView(view: View) {
        (view as RecyclerView).addOnScrollListener(
            MessageRecyclerViewListener(
                loadMessagesUseCase,
                roomId
            )
        )
    }

}