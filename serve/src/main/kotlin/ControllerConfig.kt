package com.korilin.kmm.explore.serve


private const val DEBUG_BASE_URL = "http://localhost:8888"


object ControllerPath {
    const val DOWNLOAD = "download"
}

const val DOWNLOAD_URL = "$DEBUG_BASE_URL/${ControllerPath.DOWNLOAD}/img"
