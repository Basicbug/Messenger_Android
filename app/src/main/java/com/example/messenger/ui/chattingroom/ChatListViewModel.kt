package com.example.messenger.ui.chattingroom

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.base.BaseViewModel
import com.example.messenger.ui.chattingroom.adapter.MessageRecyclerViewListener
import com.example.messenger.usecase.LoadMessagesUseCase

/**
 * @author bsgreentea
 */
class ChatListViewModel(
    private val loadMessagesUseCase: LoadMessagesUseCase,
    private val roomId: String
) : BaseViewModel {

    override fun setOnListener(view: View) {
        (view as RecyclerView).addOnScrollListener(
            MessageRecyclerViewListener(
                loadMessagesUseCase,
                roomId
            )
        )
    }
}