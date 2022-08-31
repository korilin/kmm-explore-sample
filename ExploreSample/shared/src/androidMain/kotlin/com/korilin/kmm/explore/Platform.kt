package com.korilin.kmm.explore

import com.korilin.kmm.explore.datasource.network.NetRequester

actual object Platform{
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    actual val requester: NetRequester = OkHttpNetRequester()
    actual val devEnvLocalHost: String = "192.168.3.18"
}