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

class StringListTypeConverter {

    @TypeConverter
    fun getStringListType(items: String): List<String> {
        return mutableListOf<String>().apply {
            items.takeIf { items != "" }?.split(",")?.forEach { item ->
                add(item)
            }
        }
    }

    @TypeConverter
    fun getStringType(items: List<String>): String {
        return items.takeIf { items.isNotEmpty() }?.run {
            this.first().toString() + appendRestListItemToString(items.subList(1, items.size))
        } ?: ""
    }

    private fun appendRestListItemToString(itemList: List<String>): String {
        return itemList.takeIf { itemList.isNotEmpty() }?.let {
            StringBuilder().apply {
                it.forEach { item ->
                    append(",$item")
                }
            }.toString()
        } ?: ""
    }
}