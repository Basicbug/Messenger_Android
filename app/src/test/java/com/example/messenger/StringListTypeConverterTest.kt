/*
 * StringListTypeConverterTest.kt 2020. 6. 27
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger

import com.example.messenger.repository.model.convertor.StringListTypeConverter
import org.junit.Test
import org.junit.Assert.*

/**
 * @author MyeongKi
 */

class StringListTypeConverterTest {
    private val converterTest = StringListTypeConverter()

    @Test
    fun testGetStringListType() {
        assertEquals(converterTest.getStringListType("mk,jw,bs"), listOf("mk", "jw", "bs"))
        assertEquals(converterTest.getStringListType("mk"), listOf("mk"))
        assertEquals(converterTest.getStringListType(""),  mutableListOf<String>())
    }

    @Test
    fun testGetStringType() {
        assertEquals(converterTest.getStringType(listOf("mk", "jw", "bs")), "mk,jw,bs")
        assertEquals(converterTest.getStringType(listOf("mk")), "mk")
        assertEquals(converterTest.getStringType(listOf()), "")
        assertEquals(converterTest.getStringType(mutableListOf()), "")
    }
}