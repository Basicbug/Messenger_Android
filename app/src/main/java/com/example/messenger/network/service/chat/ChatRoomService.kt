/*
 * ChatRoomService.kt 2020. 5. 9
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.network.service.chat

import com.example.messenger.repository.model.ApiDataList
import com.example.messenger.repository.model.chat.ChatRoom
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author MyeongKi
 */
interface ChatRoomService {
    @GET("")
    fun getChatRoomList(
        @Path("userId") userId: String
    ): Single<ApiDataList<ChatRoom>>
}