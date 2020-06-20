/*
 * AccessToken.kt 2020. 6. 16
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model.login

import com.google.gson.annotations.SerializedName

/**
 * @author MyeongKi
 */
class AccessToken :Token{
    @SerializedName("accessToken")
    override var token: String? = null
    @SerializedName("provider")
    override var provider: String? = null
}