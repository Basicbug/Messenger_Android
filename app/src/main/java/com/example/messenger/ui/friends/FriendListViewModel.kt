/*
 * FriendListViewModel.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.friends

import android.util.ArrayMap
import androidx.lifecycle.MutableLiveData
import com.example.messenger.base.BaseViewModel
import com.example.messenger.event.UserEvent
import com.example.messenger.repository.model.user.UserInfo
import com.example.messenger.repository.user.UserRepositoryImpl
import com.example.messenger.usecase.LoadFriendsUseCase

/**
 * @author MyeongKi
 */
//TODO 여기서는 리스트 뷰에 관련된 데이터를 뽑아오고 이를 이용하여 데이터 바인딩

class FriendListViewModel(
    userRepository: UserRepositoryImpl
) : BaseViewModel() {
    val loadFriendsUseCase = LoadFriendsUseCase(userRepository, disposables)

    val friendList = MutableLiveData<MutableList<UserInfo>>()
    private val friendTable = ArrayMap<String, UserInfo>()

    init {
        subscribeEvent()
    }

    private fun subscribeEvent() {
        disposables.add(
            UserEvent.friendInfoSubject.subscribe {
                friendTable[it.id] = it
                friendList.postValue(friendTable.values.toMutableList())
            }
        )
    }
}