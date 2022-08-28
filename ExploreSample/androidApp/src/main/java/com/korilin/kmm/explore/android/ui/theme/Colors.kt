package com.korilin.kmm.explore.android.ui.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

val defaultBackground = Color(0xfffafafa)
val PrimaryColor = Color(0xff64b5f6)
val PrimaryLight = Color(0xff9be7ff)
val PrimaryDark = Color(0xff2286c3)
val TextColor = Color(0xff000000)
val SecondaryTextColor = Color(0xff424242)
val BlueGrey = Color(0xff78909c)
val LightBlueGrey = Color(0xffb0bec5)

private val mutableAppColors: State<AppColors> = mutableStateOf(Light)
val appColors = mutableAppColors.value

sealed interface AppColors {
    val background: Color
    val primaryColor: Color
    val primaryTextColor: Color
    val secondaryTextColor: Color
    val textOnPrimary: Color
    val borderColor: Color
}

object Light : AppColors {
    override val background: Color = defaultBackground
    override val primaryColor: Color = PrimaryColor

    override val primaryTextColor: Color = TextColor
    override val secondaryTextColor: Color = SecondaryTextColor

    override val textOnPrimary: Color = TextColor
    override val borderColor: Color = LightBlueGrey
}