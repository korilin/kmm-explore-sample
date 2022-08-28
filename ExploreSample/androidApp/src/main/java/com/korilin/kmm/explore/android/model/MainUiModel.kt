package com.korilin.kmm.explore.android.model

data class TextAction(
    val text: String,
    val onClick: () -> Unit
)