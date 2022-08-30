package com.korilin.kmm.explore.android.main

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.StateObject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korilin.kmm.explore.MsgRepository
import com.korilin.kmm.explore.model.ImageMessageRecord
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    val imageMessageRecordsState = mutableStateListOf<ImageMessageRecord>()
    val messageState = mutableStateOf("")

    fun postMessage(message: String) {
        viewModelScope.launch {
            MsgRepository.postMessage(message).onSuccess {
                imageMessageRecordsState.add(it)
            }.onFailure {
                Log.e("MainViewModel", it.stackTraceToString())
            }
        }
    }

    fun updateMessage(message: String) {
        messageState.value = message
    }
}