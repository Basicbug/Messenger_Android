/*
 * StartHelper.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.start

import android.view.View
import com.example.messenger.R
import com.example.messenger.base.BaseActivity
import com.example.messenger.base.BaseHelper
import com.example.messenger.ui.friends.FriendListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * @author MyeongKi
 */

class MainHolderBottomNavigationHelper(private val activity: BaseActivity):BaseHelper{

    init {
        activity.replaceFragment(FriendListFragment::class.java, R.id.content, null)
    }

    override fun customizePropertiesView(view: View) {

        (view as BottomNavigationView).setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.friends_item -> {
                    activity.replaceFragment(FriendListFragment::class.java, R.id.content, null)
                    true
                }
                R.id.chatting_rooms_item -> {
                    true
                }
                R.id.settings_item -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

}