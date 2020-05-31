package com.example.messenger.ui.chattingroom.adapter

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

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val lastVisibleItemPosition =
            (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
        val itemTotalCount = recyclerView.adapter?.itemCount

        if (itemTotalCount != null) {
            if (lastVisibleItemPosition == 0) {
                loadMessagesUseCase.loadMessages(roomId, itemTotalCount)
//                recyclerView.smoothScrollToPosition(?)
            }
        }
    }
}