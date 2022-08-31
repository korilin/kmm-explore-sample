package com.korilin.kmm.explore.android.main

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.StateObject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korilin.kmm.explore.MsgRepository
import com.korilin.kmm.explore.datasource.storage.MessageStorageWorker
import com.korilin.kmm.explore.model.ImageMessageRecord
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*


class MainViewModel : ViewModel() {

    private val _loadingState = mutableStateOf(true)
    private val _imageMessageRecordsState = mutableStateListOf<ImageMessageRecord>()
    private val _messageState = mutableStateOf("")

    val loadingState: State<Boolean> = _loadingState
    val imageMessageRecordsState: List<ImageMessageRecord> = _imageMessageRecordsState
    val messageState: State<String> = _messageState

    fun initData() {
        viewModelScope.launch {
            MessageStorageWorker.initMessages()
            val messages = MessageStorageWorker.queryAllMessages()
            _imageMessageRecordsState.addAll(messages)
            _loadingState.value = false
        }
    }

    fun postMessage() {
        viewModelScope.launch {
            MsgRepository.postMessage(_messageState.value).onSuccess {
                _messageState.value = ""
                _imageMessageRecordsState.add(it)
                MessageStorageWorker.insertMessage(it)
            }.onFailure {
                Log.e("MainViewModel", it.stackTraceToString())
            }
        }
    }

    fun updateMessage(message: String) {
        _messageState.value = message
    }

    fun removeMessageRecord(messageRecord: ImageMessageRecord) {
        viewModelScope.launch {
            _imageMessageRecordsState.remove(messageRecord)
            MessageStorageWorker.removeMessageByTime(messageRecord.time)
        }
    }
}