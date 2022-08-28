package com.korilin.kmm.explore.android.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.korilin.kmm.explore.android.ui.theme.appColors

@Composable
fun Title(text: String, modifier: Modifier = Modifier) = Text(
    text = text,
    modifier = modifier,
    color = appColors.textOnPrimary,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
)