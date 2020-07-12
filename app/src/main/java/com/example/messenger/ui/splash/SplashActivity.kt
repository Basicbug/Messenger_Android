/*
 * SplashActivity.kt 2020. 7. 4
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.ui.splash

import android.os.Bundle
import com.example.messenger.R
import com.example.messenger.base.BaseActivity
import com.example.messenger.ui.login.LoginActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

import java.util.concurrent.TimeUnit

/**
 * @author MyeongKi
 */

class SplashActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        disposables.add(
            Observable.timer(SPLASH_TIME.toLong(), TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{finishInternal()}
        )

    }
    private fun finishInternal(){
        startActivity(LoginActivity::class.java)
        finish()
    }
    override fun finish() {
        super.finish()
        overridePendingTransition(0,  R.anim.fade_out)
    }

    companion object{
        private const val SPLASH_TIME = 1
    }
}