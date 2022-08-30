package com.korilin.kmm.explore.android.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.korilin.kmm.explore.Greeting
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.korilin.kmm.explore.android.model.TextAction
import com.korilin.kmm.explore.android.ui.screens.MainScreen

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val actions = listOf(
        TextAction("Request Data") {
            viewModel.postMessage()
        },
        TextAction("Save Locally") {}
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenContent()
        }
    }

    @Composable
    private fun ScreenContent() =
        MainScreen(actions,
            requireRecords = { viewModel.imageMessageRecordsState },
            requireMessage = { viewModel.messageState.value },
            onMessageChange = { viewModel.updateMessage(it) }
        )
}
