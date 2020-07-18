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
import androidx.lifecycle.observe
import com.example.messenger.R
import com.example.messenger.base.BaseFragment
import com.example.messenger.databinding.FragmentUsersBinding
import com.example.messenger.repository.model.user.UserInfo
import com.example.messenger.ui.users.adapter.UsersAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.Subject


/**
 * @author MyeongKi
 */

class UsersFragment : BaseFragment() {
    private lateinit var binding: FragmentUsersBinding
    private lateinit var friendsViewModel: FriendsViewModel
    private lateinit var loginUserViewModel: LoginUserViewModel

    private val friendAdapter = UsersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        binding.lifecycleOwner = this

        initViewModel()
        injectViewModel()
        injectAdapter()
        subscribeFriendInfoList(friendAdapter)
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
        binding.friendsViewModel = friendsViewModel
        binding.loginUserViewModel = loginUserViewModel
        binding.loginUserLayout.userItemViewModel = loginUserViewModel.getItemViewModel()
    }

    private fun injectAdapter() {
        binding.friends.adapter = friendAdapter
    }

    private fun subscribeFriendInfoList(adapter: UsersAdapter) {
        disposables.add(
            friendsViewModel.friendsObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {friends->
                        adapter.submitList(friends)
                    },
                    {error->
                        Log.e(this.javaClass.simpleName, error.message ?: "")
                    })
        )

    }

    private fun executeUseCase() {
        friendsViewModel.loadFriendsUseCase.loadFriends("ChoMK")
        loginUserViewModel.loadLoginUserUseCase.loadLoginUserInfo()
    }
}