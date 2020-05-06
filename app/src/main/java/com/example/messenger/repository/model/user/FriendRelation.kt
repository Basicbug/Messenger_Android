/*
 * FriendRelation.kt 2020. 5. 5
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
data class FriendRelation(
    @PrimaryKey
    @SerializedName("id")
    var id: String = "",
    @SerializedName("uid")
    override var uid: String? = "",
    @SerializedName("frienduid")
    var friendUid: String? = "",
    @SerializedName("meetAt")
    var meetAt: String? = ""
):User