package com.korilin.kmm.explore

import platform.UIKit.UIDevice

actual object Platform{
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    actual val devEnvLocalHost: String ="127.0.0.1"
}