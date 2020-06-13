/*
 * Token.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model.login

import com.google.gson.annotations.SerializedName

/**
 * @author MyeongKi
 */
class Token {
    @SerializedName("jwtToken")
    var jwtToken: String? = null

    @SerializedName("accessToken")
    var accessToken: String? = null

    @SerializedName("provider")
    var provider: String? = null
}