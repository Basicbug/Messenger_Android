package com.example.messenger.ui.chatroom.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.usecase.LoadMessagesUseCase

/**
 * @author bsgreentea
 */

class MessageRecyclerViewListener(
    private val loadMessagesUseCase: LoadMessagesUseCase,
    private val roomId: String
) : RecyclerView.OnScrollListener() {

    companion object {
        private const val MIN_SIZE_OF_MESSAGES: Int = 50
        private const val LOAD_MESSAGE_POINT: Int = 15
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val firstVisibleItemPosition =
            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        val itemTotalCount = recyclerView.adapter?.itemCount

        itemTotalCount?.let {

            if (it >= MIN_SIZE_OF_MESSAGES) {

                if (firstVisibleItemPosition == LOAD_MESSAGE_POINT) {
                    loadMessagesUseCase.loadMessages(roomId, it)
                }
            }
        }

    }
}