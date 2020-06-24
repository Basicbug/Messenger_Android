/*
 * BaseSocketActivity.kt 2020. 5. 30
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.base

import com.example.messenger.network.SocketHelper

/**
 * @author MyeongKi
 */

abstract class BaseSocketActivity : BaseActivity(){
    override fun onResume() {
        super.onResume()
        SocketHelper.connectSocket()
    }

    override fun onPause() {
        super.onPause()
        SocketHelper.disconnectSocket()
    }
}