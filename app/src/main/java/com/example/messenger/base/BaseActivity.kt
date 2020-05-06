/*
 * BaseActivity.kt 2020. 4. 22
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.messenger.tools.NavigationDelegate

/**
 * @author MyeongKi
 */

abstract class BaseActivity : AppCompatActivity(), NavigationDelegate {

    override fun replaceFragmentSaved(target: Class<out Fragment>, containerId: Int, arguments: Bundle?) {
        val tag = target.name
        val fragment = target.newInstance().also { fragment ->
            if (fragment is BaseFragment) {
                fragment.navigationDelegate = this
            }
        }
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(containerId, fragment, tag)
            .commit()
    }

    override fun replaceFragment(target: Class<out Fragment>, containerId: Int, arguments: Bundle?) {
        val tag = target.name
        val fragment = target.newInstance().also { fragment ->
            if (fragment is BaseFragment) {
                fragment.navigationDelegate = this
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(containerId, fragment, tag)
            .commit()
    }

    override fun startActivity(target: Class<out Activity>, extras: Bundle?) {
        startActivity(Intent(this, target))
    }
}