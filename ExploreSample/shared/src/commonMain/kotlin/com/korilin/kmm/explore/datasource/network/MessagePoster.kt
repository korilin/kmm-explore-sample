package com.korilin.kmm.explore.datasource.network

import com.korilin.kmm.explore.model.ImageMessage
import com.korilin.kmm.explore.model.PostMessage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.Json

private const val POST_MSG_KEY_DEVICE = "device"
private const val POST_MSG_KEY_MSG = "msg"

class MessagePoster(
    private val requester: NetRequester
) {

    private val baseUrl = REMOTE_URL

    suspend fun postMsg(message: PostMessage): Result<ImageMessage> {
        val url = baseUrl.plus(POST_MSG_PATH)
        val result = requester.post(
            url, mapOf(
                POST_MSG_KEY_DEVICE to message.device,
                POST_MSG_KEY_MSG to message.msg
            )
        )
        return decodeBody(result)
    }

    private inline fun <reified T> decodeBody(result: Result<String>) = if (result.isSuccess) {
        result.getOrNull()?.let {
            val imageMessage = Json.decodeFromString<T>(it)
            Result.success(imageMessage)
        } ?: Result.failure(NullPointerException("message is null"))
    } else Result.failure(
        result.exceptionOrNull() ?: NullPointerException("Result value is null")
    )
}