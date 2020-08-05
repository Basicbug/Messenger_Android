/*
 * ConvertTools.kt 2020. 5. 16
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.util

import com.example.messenger.repository.model.chat.Message
import com.google.gson.Gson

/**
 * @author MyeongKi

 */

fun convertMessageToJsonString(msg: Message?): String? {
    return msg?.let {
        Gson().toJson(msg)
    }
}

fun convertJsonStringToMessage(jsonString: String?): Message? {
    return jsonString?.let {
        Gson().fromJson<Message>(jsonString, Message::class.java)
    }

}
