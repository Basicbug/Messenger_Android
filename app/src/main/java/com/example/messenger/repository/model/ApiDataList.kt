/*
 * ApiDataList.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model

import com.google.gson.annotations.SerializedName

/**
 * @author MyeongKi
 */

class ApiDataList<T> {
    @SerializedName("code")
    val code: Int = 0
    @SerializedName("dataList")
    val dataList: ArrayList<T>? = null
    @SerializedName("message")
    val message: String = ""
    @SerializedName("success")
    val success:Boolean = false
}