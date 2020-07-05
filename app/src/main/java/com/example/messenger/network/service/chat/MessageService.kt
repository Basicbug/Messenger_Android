/*
 * MessageService.kt 2020. 6. 28
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.network.service.chat

import com.example.messenger.repository.model.ApiData
import com.example.messenger.repository.model.chat.Message
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author MyeongKi
 */

interface MessageService {

    @GET("/v1/talk/message")
    fun getMessage(
        @Query("messageId") messageId: String,
        @Query("roomId") roomId: String
    ): Single<ApiData<Message>>
}