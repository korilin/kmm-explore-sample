package com.korilin.kmm.explore.android.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.korilin.kmm.explore.model.ImageMessage


class MainViewModel : ViewModel(){
    val randomDataState = mutableStateListOf<ImageMessage>()


}