/*
 * FriendListFragment.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.messenger.R
import com.example.messenger.base.BaseFragment
import com.example.messenger.databinding.FragmentFriendListBinding

/**
 * @author MyeongKi
 */

class FriendListFragment :BaseFragment(){
    private lateinit var binding:FragmentFriendListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friend_list, container, false)
        return binding.root
    }
}