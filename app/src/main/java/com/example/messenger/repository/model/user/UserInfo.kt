/*
 * UserInfo.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model.user

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author MyeongKi
 */
@Entity
data class UserInfo(
    @PrimaryKey
    @SerializedName("id")
    override var id: String = "",
    @SerializedName("name")
    var name: String? ="",
    @SerializedName("imageUrl")
    var imageUrl: String? = "",
    @SerializedName("stateMessage")
    var stateMessage: String? = "",
    @SerializedName("uid")
    override var uid: String? = "",
    @SerializedName("roles")
    @Ignore
    val roles: List<String> = Collections.emptyList(),
    @SerializedName("provider")
    var provider: String? = ""
 ):User