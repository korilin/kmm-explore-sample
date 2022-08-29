package com.korilin.kmm.explore

import com.korilin.kmm.explore.datasource.network.MessagePoster

object MsgRepository {

    private val poster = MessagePoster(OkHttpNetRequester())

    suspend fun postMessage(message: String)  = poster.postMsg(message)

}