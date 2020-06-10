package com.example.messenger.ui.chattingroom.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger.constants.AppInfoConstants
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

        val firstVisibleItemPosition =
            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        val itemTotalCount = recyclerView.adapter?.itemCount

        if (itemTotalCount != null) {

            if (itemTotalCount >= AppInfoConstants.MIN_SIZE_OF_MESSAGES) {

                if (firstVisibleItemPosition == AppInfoConstants.LOAD_MESSAGE_POINT) {
                    loadMessagesUseCase.loadMessages(roomId, itemTotalCount)
                }
            }
        }

    }
}