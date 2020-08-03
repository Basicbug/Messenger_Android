/*
 * FriendListFragment.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.messenger.R
import com.example.messenger.base.BaseFragment
import com.example.messenger.databinding.FragmentLoginUserFriendsBinding
import com.example.messenger.manager.NaverLoginManager
import com.example.messenger.tools.errorLog
import com.example.messenger.ui.users.adapter.UsersAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.common_app_bar.view.*


/**
 * @author MyeongKi
 */

class LoginUserFriendsFragment : BaseFragment() {
    private lateinit var binding: FragmentLoginUserFriendsBinding
    private lateinit var friendsViewModel: FriendsViewModel
    private lateinit var loginUserViewModel: LoginUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_user_friends, container, false)
        binding.lifecycleOwner = this
        injectViewModel()
        injectAdapter()
        executeUseCase()
        return binding.root
    }

    private fun initViewModel() {
        friendsViewModel =
            UserViewModelInjector.provideUsersViewModelFactory()
                .create(FriendsViewModel::class.java)
        loginUserViewModel =
            UserViewModelInjector.provideUsersViewModelFactory()
                .create(LoginUserViewModel::class.java)

    }

    private fun injectViewModel() {
        initViewModel()
        binding.apply {
            friendsViewModel = friendsViewModel
            loginUserViewModel = loginUserViewModel
            loginUserLayout.userItemViewModel = loginUserViewModel?.getItemViewModel()
        }
    }

    private fun injectAdapter() {
        binding.apply {
            friends.adapter = UsersAdapter().also {
                subscribeFriendInfoList(it)
            }
        }
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
}