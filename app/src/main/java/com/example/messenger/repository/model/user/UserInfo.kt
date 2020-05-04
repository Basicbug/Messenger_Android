/*
 * UserInfo.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author MyeongKi
 */
@Entity
data class UserInfo(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("stateMessage")
    val stateMessage: String,
    @SerializedName("uid")
    override val uid: String,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("provider")
    val provider: String
 ):User