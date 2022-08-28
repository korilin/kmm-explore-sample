package com.korilin.kmm.explore.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.korilin.kmm.explore.android.ui.theme.appColors

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) = Button(
    onClick = onClick,
    modifier,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = appColors.primaryColor,
        contentColor = appColors.textOnPrimary
    ),
    content = content
)

@Preview
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(onClick = { /*TODO*/ }) {
        Text(text = "hello", color = appColors.textOnPrimary)
    }
}