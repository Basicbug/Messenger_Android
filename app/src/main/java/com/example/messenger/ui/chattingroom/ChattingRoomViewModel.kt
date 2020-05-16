package com.example.messenger.ui.chattingroom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.messenger.repository.message.MessageRepositoryImpl
import com.example.messenger.repository.model.Message
import io.reactivex.disposables.CompositeDisposable

/**
 * @author bsgreentea
 */
class ChattingRoomViewModel(
    val repository: MessageRepositoryImpl
) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()
        //val loadChattingRoomUseCase = ChattingRoomUseCase()
    val messageList = MutableLiveData<ArrayList<Message>>().apply {
        value = ArrayList()
    }

    private var messageToSend: ObservableField<String>

    init {
        subscribeEvent()
        messageToSend = ObservableField("")
    }

    fun onClickSend() {

    }

    fun getText(): ObservableField<String>{
        return messageToSend
    }

    private fun subscribeEvent() {

    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}