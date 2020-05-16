/*
 * ConvertTools.kt 2020. 5. 16
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.tools

import com.example.messenger.repository.model.Message
import com.example.messenger.type.MessageType

/**
 * @author MyeongKi

 */

fun convertMessageToJsonString(msg: Message): String {
    //TODO json 스트링 로직 필요
    return msg.toString()
}

fun convertJsonStringToMessage(jsonString: String): Message {
    //TODO string으로 받은 json을 메시지로 전환이 필요
    return Message(1, "test", "test", MessageType.MESSAGE, "teststs", "")
}
