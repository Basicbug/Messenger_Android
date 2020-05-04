/*
 * FriendRelationRepository.kt 2020. 5. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.user

import com.example.messenger.repository.model.user.FriendRelation
import io.reactivex.Single

/**
 * @author MyeongKi
 */
interface FriendRelationRepository {
    fun getFriendRelationListFromServer(userId: String): Single<ArrayList<FriendRelation>>

}