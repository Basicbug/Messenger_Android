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
import com.example.messenger.ui.chat.chatroomlist.ChatRoomListFragment
import com.example.messenger.ui.users.LoginUserFriendsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * @author MyeongKi
 */

class MainHolderBottomNavigationHelper(private val activity: BaseActivity):BaseHelper{

    init {
        activity.replaceFragment(LoginUserFriendsFragment::class.java, R.id.content, null)
    }

    override fun customizePropertiesView(view: View) {

        (view as BottomNavigationView).setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.friends_item -> {
                    activity.replaceFragment(LoginUserFriendsFragment::class.java, R.id.content, null)
                    true
                }
                R.id.chatting_rooms_item -> {
                    activity.replaceFragment(ChatRoomListFragment::class.java, R.id.content, null)
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