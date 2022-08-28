package com.korilin.kmm.explore.datasource.network

import com.korilin.kmm.explore.model.ImageMessage
import com.korilin.kmm.explore.model.PostMessage

private const val POST_MSG_KEY_DEVICE = "device"
private const val POST_MSG_KEY_MSG = "msg"

class MessagePoster(
    private val requester: NetRequester
) {

    private val baseUrl = REMOTE_URL

    suspend fun postMsg(message: PostMessage): Result<ImageMessage> {
        val url = REMOTE_URL.plus(POST_MSG_PATH)
        return requester.post(
            url, mapOf(
                POST_MSG_KEY_DEVICE to message.device,
                POST_MSG_KEY_MSG to message.msg
            )
        )
    }
}