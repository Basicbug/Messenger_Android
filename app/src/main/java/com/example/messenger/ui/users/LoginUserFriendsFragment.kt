/*
 * FriendListFragment.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.messenger.R
import com.example.messenger.app.ToastHelper
import com.example.messenger.base.BaseFragment
import com.example.messenger.common.event.DefaultItemActionEvent
import com.example.messenger.databinding.FragmentLoginUserFriendsBinding
import com.example.messenger.repository.model.user.UserInfo
import com.example.messenger.type.ItemEvent
import com.example.messenger.util.errorLog
import com.example.messenger.ui.users.adapter.UsersAdapter
import com.example.messenger.util.bus.RxAction
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers


/**
 * @author MyeongKi
 */

class LoginUserFriendsFragment : BaseFragment() {
    private lateinit var binding: FragmentLoginUserFriendsBinding
    private lateinit var friendsViewModel: FriendsViewModel
    private lateinit var loginUserViewModel: LoginUserViewModel

    private val itemEventRelay = PublishRelay.create<RxAction>()
    private val friendAdapter = UsersAdapter(itemEventRelay)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_user_friends, container, false)
        binding.lifecycleOwner = this
        initViewModel()
        injectViewModel()
        injectAdapter()
        subscribeFriendInfoList(friendAdapter)
        executeUseCase()
        observeEvent()
        return binding.root
    }

    private fun initViewModel() {
        friendsViewModel =
            UserViewModelInjector.provideUsersViewModelFactory()
                .create(FriendsViewModel::class.java)
        loginUserViewModel =
            UserViewModelInjector.provideUsersViewModelFactory()
                .create(LoginUserViewModel::class.java).apply {
                    this.getItemViewModel().itemEventRelay = itemEventRelay
                }

    }

    private fun injectViewModel() {
        binding.apply {
            friendsViewModel = friendsViewModel
            loginUserViewModel = loginUserViewModel
            loginUserLayout.userItemViewModel = loginUserViewModel?.getItemViewModel()
        }
    }

    private fun injectAdapter() {
        binding.friends.adapter = friendAdapter
    }

    private fun subscribeFriendInfoList(adapter: UsersAdapter) {
        disposables.add(
            friendsViewModel.friendsObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { friends ->
                        adapter.submitList(friends)
                    },
                    { error ->
                        errorLog(this, error)
                    })
        )

    }

    private fun executeUseCase() {
        friendsViewModel.loadFriendsUseCase.loadFriends()
        loginUserViewModel.loadLoginUserUseCase.loadLoginUserInfo()
    }

    private fun observeEvent() {
        disposables.add(itemEventRelay.ofType(DefaultItemActionEvent::class.java)
            .filter { it.getEventType() == ItemEvent.CLICK.type }
            .subscribe {
                ToastHelper.show((it.getValue() as UserInfo).name ?: "")
            })


    }
}