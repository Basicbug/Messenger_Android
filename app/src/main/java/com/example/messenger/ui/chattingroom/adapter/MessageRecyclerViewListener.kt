package com.example.messenger.ui.chattingroom.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author bsgreentea
 */
object MessageRecyclerViewListener : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val lastVisibleItemPosition =
            (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
        val itemTotalCount = recyclerView.adapter?.itemCount

        if (itemTotalCount != null) {
            if (lastVisibleItemPosition == 0) {
                // TODO 메세지 더 불러오기
//                recyclerView.smoothScrollToPosition(?)
            }
        }
    }
}