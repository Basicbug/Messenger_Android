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
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * @author MyeongKi
 */

class StartBottomNavigationHelper(val activity: BaseActivity):BaseHelper{


    override fun setOnListener(view: View) {

        (view as BottomNavigationView).setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_one -> {
                    true
                }
                R.id.action_two -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

}