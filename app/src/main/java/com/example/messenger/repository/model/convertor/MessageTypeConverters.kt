/*
 * MessageTypeConverters.kt 2020. 5. 16
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model.convertor

import androidx.room.TypeConverter
import com.example.messenger.type.MessageType

/**
 * @author MyeongKi
 */
class MessageTypeConverters {
    @TypeConverter
    fun getMessageType(code: Int): MessageType {
        for (item in MessageType.values()) {
            if (item.code == code) {
                return item
            }
        }
        return MessageType.FAIL
    }

    @TypeConverter
    fun getMessageTypeCode(messageType: MessageType?): Int {
        return messageType?.code?:-1
    }
}