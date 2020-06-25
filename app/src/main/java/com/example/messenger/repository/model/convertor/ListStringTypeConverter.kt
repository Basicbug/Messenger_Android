/*
 * ListTypeConverter.kt 2020. 6. 24
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model.convertor

import androidx.room.TypeConverter
import java.lang.StringBuilder

/**
 * @author MyeongKi
 */

class ListStringTypeConverter {

    @TypeConverter
    fun getListType(itemListString: String): List<String> {
        return mutableListOf<String>().apply {
            itemListString.split(",".toRegex()).forEach { item ->
                add(item)
            }
        }
    }

    @TypeConverter
    fun getAppendedListStringType(itemList: List<String>): String {
        return when {
            itemList.isEmpty() -> {
                ""
            }
            else -> {
                itemList.first().toString() + appendRestListItemToString(itemList.subList(1, itemList.size))
            }
        }
    }

    private fun appendRestListItemToString(itemList: List<String>): String {
        return when {
            itemList.isEmpty() -> {
                ""
            }
            else -> {
                StringBuilder().apply {
                    itemList.forEach {
                        append(",{$it}")
                    }
                }.toString()
            }
        }
    }
}