package com.korilin.kmm.explore.android.ui.screens

import android.text.format.DateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
fun MainScreen(
    buttonsActions: List<TextAction>,
    requireRecords: () -> List<ImageMessageRecord>,
    requireMessage: () -> String,
    onMessageChange: (String) -> Unit
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

        RecordList(requireRecords)
    }
}

@Composable
fun RecordList(
    requireRecords: () -> List<ImageMessageRecord>
) = LazyColumn {
    val records = requireRecords()
    items(records, key = { it.time }) {
        ImageMessageRecord(it)
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun ImageMessageRecord(item: ImageMessageRecord) = Card(
    modifier = Modifier.wrapContentSize()
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
fun MainScreenPreview() = MainScreen(
    buttonsActions = listOf(
        TextAction("Hi") {},
        TextAction("Kotlin") {}
    ),
    requireRecords = {
        listOf(
            ImageMessageRecord(
                time = 1661698266062,
                img = "http://localhost:8888/download/img/File3.jpg",
                msg = "Android: hi"
            ),
            ImageMessageRecord(
                time = 1661698266065,
                img = "http://localhost:8888/download/img/File3.jpg",
                msg = "Android: hi"
            )
        )
    },
    requireMessage = { "" },
    onMessageChange = {}
)

@Preview
@Composable
fun RandomItemPreview() = ImageMessageRecord(
    item = ImageMessageRecord(
        time = 1661693205628,
        img = "http://localhost:8888/download/img/File3.jpg",
        msg = "Android: hi"
    )
)