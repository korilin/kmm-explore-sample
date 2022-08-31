package com.korilin.kmm.explore

import com.korilin.kmm.explore.datasource.network.NetRequester

expect object Platform {
    val platform: String
    val requester: NetRequester
    val devEnvLocalHost: String
}