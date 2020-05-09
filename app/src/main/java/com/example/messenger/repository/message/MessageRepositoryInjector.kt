package com.example.messenger.repository.message

/**
 * @author bsgreentea
 */
object MessageRepositoryInjector {
    fun getMessageRepositoryImpl(): MessageRepositoryImpl {
        return MessageRepositoryImpl.getInstance()
    }
}