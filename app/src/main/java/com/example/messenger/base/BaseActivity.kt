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
import com.example.messenger.util.NavigationDelegate
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */

abstract class BaseActivity : AppCompatActivity(), NavigationDelegate {
    protected val disposables: CompositeDisposable = CompositeDisposable()


    override fun replaceFragmentSavedInBackStack(target: Class<out Fragment>, containerId: Int, arguments: Bundle?) {
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

    override fun replaceFragmentSaved(target: Class<out Fragment>, containerId: Int, arguments: Bundle?) {
        val currentFragment = supportFragmentManager.primaryNavigationFragment
        val transaction = supportFragmentManager.beginTransaction()

        currentFragment?.let {
            transaction.detach(it)
        }
        val tag = target.name
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = target.newInstance().also { newFragment ->
                if (newFragment is BaseFragment) {
                    newFragment.navigationDelegate = this
                }
                transaction
                    .add(containerId, newFragment, tag)
            }
        } else {
            transaction.attach(fragment)
        }
        transaction.setPrimaryNavigationFragment(fragment);
        transaction.setReorderingAllowed(true);
        transaction.commitNowAllowingStateLoss();
    }


    override fun startActivity(target: Class<out Activity>, extras: Bundle?) {
        startActivity(Intent(this, target))
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}