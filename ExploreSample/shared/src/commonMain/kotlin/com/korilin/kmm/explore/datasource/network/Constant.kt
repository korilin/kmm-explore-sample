package com.korilin.kmm.explore.datasource.network

import com.korilin.kmm.explore.Platform

const val REMOTE_SERVE_POINT = "8888"
val REMOTE_URL = "http://${Platform.devEnvLocalHost}:$REMOTE_SERVE_POINT"
const val POST_MSG_PATH = "/post/msg"
