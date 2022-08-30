package com.korilin.kmm.explore

actual object Platform{
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    actual val devEnvLocalHost: String = "192.168.3.18"
}