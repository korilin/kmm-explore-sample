package com.korilin.kmm.explore.android.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.korilin.kmm.explore.Greeting
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.korilin.kmm.explore.android.model.TextAction
import com.korilin.kmm.explore.android.ui.screens.MainScreen
import com.korilin.kmm.explore.android.ui.theme.appColors

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
            if (viewModel.loadingState.value) {
                LoadingContent()
            } else {
                ScreenContent()
            }
        }
        viewModel.initData()
    }

    @Composable
    private fun LoadingContent() =
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = appColors.primaryColor,
                    strokeWidth = 5.dp,
                )
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
