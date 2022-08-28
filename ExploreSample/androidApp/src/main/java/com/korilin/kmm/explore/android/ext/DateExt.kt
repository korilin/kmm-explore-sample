package com.korilin.kmm.explore.android.ext

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
infix fun Date.format(string: String): String {
    return SimpleDateFormat.getPatternInstance(string).format(this)
}