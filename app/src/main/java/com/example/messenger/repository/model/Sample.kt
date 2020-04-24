/*
 * Sample.kt 2020. 4. 22
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author MyeongKi
 */

@Entity
data class Sample(
    @SerializedName("age")
    val age:Int = 0,
    @SerializedName("email")
    val email:String = "",
    @SerializedName("name")
    @PrimaryKey
    val name:String = ""
)