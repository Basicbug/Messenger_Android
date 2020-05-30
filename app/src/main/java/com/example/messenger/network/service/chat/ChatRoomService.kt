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
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author MyeongKi
 */
interface ChatRoomService {
    @POST("/v1/talk/room/list")
    fun getChatRoomList(
        @Query("uid") userId: String
    ): Single<ApiDataList<ChatRoom>>
}