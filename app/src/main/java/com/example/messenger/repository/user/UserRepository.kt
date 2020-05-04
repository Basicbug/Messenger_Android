/*
 * UserRepository.kt 2020. 5. 4
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.user

import com.example.messenger.repository.model.User
import io.reactivex.Single

/**
 * @author MyeongKi
 */

interface UserRepository {
    fun getFriends(userId: String): Single<ArrayList<User>>
}