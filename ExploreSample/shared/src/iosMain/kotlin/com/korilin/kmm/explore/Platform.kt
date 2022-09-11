package com.korilin.kmm.explore

import com.korilin.kmm.explore.datasource.network.NetRequester
import platform.UIKit.UIDevice

actual object Platform{
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    actual val devEnvLocalHost: String ="192.168.3.18"
    actual val requester: NetRequester = KtorNetRequester()
}