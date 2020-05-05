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
import com.example.messenger.repository.user.UserRepositoryImpl
import com.example.messenger.usecase.LoadFriendsUseCase
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */
//TODO 여기서는 리스트 뷰에 관련된 데이터를 뽑아오고 이를 이용하여 데이터 바인딩

class FriendListViewModel(
    userRepository: UserRepositoryImpl
) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()
    val loadFriendsUseCase = LoadFriendsUseCase("ChoMk", userRepository, disposables)
    val testResult = MutableLiveData<String>()//TODO 이벤트 발생 캐치 테스트

    init {
        disposables.add(
            FriendEvent.addFriendToListSubject.subscribe {
                testResult.value += ("\n"+it.toString())
            }
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}