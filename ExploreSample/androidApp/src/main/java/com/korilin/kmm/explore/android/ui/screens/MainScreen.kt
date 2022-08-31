package com.korilin.kmm.explore.android.ui.screens

import android.text.format.DateFormat
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.korilin.kmm.explore.android.model.TextAction
import com.korilin.kmm.explore.android.ui.component.EditText
import com.korilin.kmm.explore.android.ui.component.PrimaryButton
import com.korilin.kmm.explore.android.ui.component.Title
import com.korilin.kmm.explore.android.ui.theme.appColors
import com.korilin.kmm.explore.model.ImageMessageRecord
import java.util.*
import kotlin.math.roundToInt

@Composable
fun MainScreen(
    buttonsActions: List<TextAction>,
    requireRecords: () -> List<ImageMessageRecord>,
    requireMessage: () -> String,
    onMessageChange: (String) -> Unit,
    onMessageRemove: (ImageMessageRecord) -> Unit,
) = Box(
    modifier = Modifier
        .background(appColors.background)
        .fillMaxHeight()
        .fillMaxWidth()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(25.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
    ) {
        Title(text = "Explore Sample")
        Divider(modifier = Modifier.padding(vertical = 20.dp))

        EditText(
            requireValue = requireMessage,
            onValueChange = onMessageChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(bottom = 20.dp)
                .background(appColors.editorBackground)
        )

        for (textBtn in buttonsActions) {
            PrimaryButton(onClick = textBtn.onClick, modifier = Modifier.fillMaxWidth()) {
                Text(text = textBtn.text, color = appColors.textOnPrimary)
            }
        }

        Divider(modifier = Modifier.padding(vertical = 20.dp))

        RecordList(requireRecords, onMessageRemove)
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun RecordList(
    requireRecords: () -> List<ImageMessageRecord>,
    onRemove: (ImageMessageRecord) -> Unit = {}
) = LazyColumn {
    val records = requireRecords()
    items(records, key = { it.time }) {
        val removePx = -with(LocalDensity.current) { 100.dp.toPx() }
        val swipeState = rememberSwipeableState(false)
        val anchors = mapOf(removePx to true, 0f to false)
        if (swipeState.currentValue) {
            onRemove.invoke(it)
        }
        Box(
            modifier = Modifier
                .animateItemPlacement()
                .swipeable(
                    state = swipeState,
                    orientation = Orientation.Horizontal,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.8f) }
                ),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                Icons.Filled.Cancel,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .padding(end = 20.dp)
            )
            ImageMessageRecord(it, modifier = Modifier.offset {
                IntOffset(swipeState.offset.value.roundToInt(), 0)
            })
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun ImageMessageRecord(
    item: ImageMessageRecord,
    modifier: Modifier = Modifier
) = Card(
    modifier = modifier.wrapContentSize()
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(20.dp),
    ) {
        val datetime = remember(key1 = item.time) {
            DateFormat.format("yyyy-MM-dd HH:mm:ss", Date(item.time)).toString()
        }
        Text(
            text = datetime,
            fontSize = 10.sp,
            color = appColors.secondaryTextColor
        )
        Text(
            text = item.msg, fontSize = 14.sp,
            modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
            color = appColors.primaryTextColor
        )
        SubcomposeAsyncImage(
            model = item.img,
            contentDescription = item.msg,
            loading = {
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = appColors.primaryColor,
                        strokeWidth = 5.dp,
                    )
                }
            },
            modifier = Modifier
                .width(300.dp)
                .height(150.dp)
        )
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    val list = remember {
        mutableStateListOf(
            ImageMessageRecord(
                time = Date().time,
                img = "http://192.168.3.18:8888/download/img/File9.jpg",
                msg = "Android: hi"
            )
        )
    }
    MainScreen(
        buttonsActions = listOf(
            TextAction("Send Message") {
                list.add(
                    ImageMessageRecord(
                        time = 1661698266065,
                        img = "http://192.168.3.18:8888/download/img/File9.jpg",
                        msg = "Android: hi"
                    )
                )
            },
            TextAction("Kotlin") {}
        ),
        requireRecords = { list },
        requireMessage = { "" },
        onMessageChange = {},
        onMessageRemove = {}
    )
}

@Preview
@Composable
fun RandomItemPreview() = ImageMessageRecord(
    item = ImageMessageRecord(
        time = 1661693205628,
        img = "http://localhost:8888/download/img/File3.jpg",
        msg = "Android: hi"
    )
)