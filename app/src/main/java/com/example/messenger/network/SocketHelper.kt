/*
 * SocketHelper.kt 2020. 5. 16
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.network

import com.example.messenger.constants.NetworkConstants.SOCKET_URL
import com.example.messenger.repository.model.chat.Message
import com.example.messenger.util.convertMessageToJsonString
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

    fun connectSocket() {
        stompClient.connect()
    }

    fun disconnectSocket() {
        stompClient.disconnect()
    }

    fun createChattingRoomStream(roomId: String): Flowable<StompMessage> {
        return stompClient.topic("/sub/talk/room/$roomId")
    }

    fun createMessageSendStream(msg: Message): Completable {
        return stompClient.send("/pub/talk/message", convertMessageToJsonString(msg))
    }
}