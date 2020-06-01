/*
 * DateTypeConverter.kt 2020. 6. 1
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model.convertor

import androidx.room.TypeConverter
import java.util.*

/**
 * @author MyeongKi
 */

object DateTypeConverter {
    @JvmStatic
    @TypeConverter
    fun toDate(timeStamp: Long?): Date? {
        return timeStamp?.let {
            Date(timeStamp)
        }
    }

    @JvmStatic
    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.let {
            date.time
        }
    }
}