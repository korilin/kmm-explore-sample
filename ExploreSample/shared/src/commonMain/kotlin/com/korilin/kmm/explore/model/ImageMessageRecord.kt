package com.korilin.kmm.explore.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageMessageRecord(
    @SerialName("title") val time: Long = 0L,
    @SerialName("img") val img: String,
    @SerialName("msg") val msg: String
)