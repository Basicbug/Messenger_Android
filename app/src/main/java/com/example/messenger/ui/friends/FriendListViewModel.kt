/*
 * FriendListViewModel.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.friends

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.messenger.event.FriendEvent
import com.example.messenger.repository.model.user.UserInfo
import com.example.messenger.repository.user.UserRepositoryImpl
import com.example.messenger.usecase.LoadFriendsUseCase
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author MyeongKi
 */
//TODO 여기서는 리스트 뷰에 관련된 데이터를 뽑아오고 이를 이용하여 데이터 바인딩

class FriendListViewModel(
    userRepository: UserRepositoryImpl
) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()
    val loadFriendsUseCase = LoadFriendsUseCase("ChoMk", userRepository, disposables)
    val friendList = MutableLiveData<ArrayList<UserInfo>>().apply {
        value = ArrayList()
    }

    init {
        subscribeEvent()
    }

    private fun subscribeEvent(){
        disposables.add(
            FriendEvent.addFriendToListSubject.subscribe {
                friendList.value?.add(it)
                friendList.postValue(friendList.value)
            }
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}