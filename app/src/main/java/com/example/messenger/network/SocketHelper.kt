/*
 * SocketHelper.kt 2020. 5. 16
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.network

import com.example.messenger.constants.NetworkConstants.SOCKET_URL
import com.example.messenger.repository.model.Message
import com.example.messenger.tools.convertMessageToJsonString
import io.reactivex.Completable
import io.reactivex.Flowable
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.StompMessage

/**
 * @author MyeongKi
 */
object SocketHelper {
    private val stompClient: StompClient = Stomp.over(
        Stomp.ConnectionProvider.JWS,
        SOCKET_URL
    )

    fun createChattingRoomStream(roomID: String): Flowable<StompMessage> {
        return stompClient.topic("/sub/talk/room/$roomID")
    }

    fun createMessageSendStream(msg: Message): Completable {
        return stompClient.send("/pub/talk/room", convertMessageToJsonString(msg))
    }
}