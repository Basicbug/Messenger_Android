/*
 * BaseFragment.kt 2020. 5. 3
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.messenger.util.NavigationDelegate
import io.reactivex.disposables.CompositeDisposable

/**
 * @author MyeongKi
 */

abstract class BaseFragment : Fragment() {
    lateinit var navigationDelegate: NavigationDelegate
    protected val disposables: CompositeDisposable = CompositeDisposable()

    fun replaceFragment(target: Class<out Fragment>, containerId: Int, arguments: Bundle?) {
        if (!this::navigationDelegate.isInitialized) return
        navigationDelegate.replaceFragment(target, containerId, arguments)
    }

    fun replaceFragmentSaved(target: Class<out Fragment>, containerId: Int, arguments: Bundle?) {
        if (!this::navigationDelegate.isInitialized) return
        navigationDelegate.replaceFragmentSavedInBackStack(target, containerId, arguments)
    }

    fun startActivity(target: Class<out Activity>, extras: Bundle? = null) {
        val intent = Intent(requireContext(), target)
        extras?.let {
            intent.putExtras(extras)
        }
        startActivity(intent)
    }
    override fun onDestroy() {
        super.onDestroy()
        if(!disposables.isDisposed){
            disposables.dispose()
        }
    }
}