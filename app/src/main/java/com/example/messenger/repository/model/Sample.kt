/*
 * Sample.kt 2020. 4. 22
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * @author MyeongKi
 */

@Entity
data class Sample(
    @SerializedName("testResult") val testResult: String?
)