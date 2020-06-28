/*
 * MessageRepositoryInjector.kt 2020. 6. 28
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.repository.chat

/**
 * @author bsgreentea
 */
object MessageRepositoryInjector {
    fun getMessageRepositoryImpl(): MessageRepositoryImpl {
        return MessageRepositoryImpl.getInstance()
    }
}