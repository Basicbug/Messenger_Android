/*
 * ListTypeConverter.kt 2020. 6. 24
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model.convertor

import androidx.room.TypeConverter

/**
 * @author MyeongKi
 */

class ListStringTypeConverter {

    @TypeConverter
    fun getListType(itemListString: String): List<String> {
        return mutableListOf<String>().apply {
            itemListString.split(",").forEach { item ->
                this.add(item)
            }
        }
    }

    @TypeConverter
    fun getAppendedListStringType(itemList: List<String>): String {
        return when {
            itemList.isEmpty() -> {
                ""
            }
            itemList.size == 1 -> {
                itemList.first().toString()
            }
            else -> {
                itemList.first().toString() + appendRestListItemToString(itemList.subList(1, itemList.size))
            }
        }
    }

    private fun appendRestListItemToString(itemList: List<String>): String {
        var result = ""
        itemList.forEach {
            result = "$result,${it}"

        }
        return result
    }
}