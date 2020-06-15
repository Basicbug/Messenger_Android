/*
 * Token.kt 2020. 6. 10
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.model.login


/**
 * @author MyeongKi
 */
interface Token {
    var token: String?
    var provider: String?
}