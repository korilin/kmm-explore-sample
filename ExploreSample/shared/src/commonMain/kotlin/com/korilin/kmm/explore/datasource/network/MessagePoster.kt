package com.korilin.kmm.explore.datasource.network

import com.korilin.kmm.explore.Platform
import com.korilin.kmm.explore.model.ImageMessageRecord
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private const val POST_MSG_KEY_DEVICE = "device"
private const val POST_MSG_KEY_MSG = "msg"

object MessagePoster{

    private val requester = Platform.requester
    private val baseUrl = REMOTE_URL
    private val jsonParser = Json {
        ignoreUnknownKeys = true
    }

    suspend fun postMessage(message: String): Result<ImageMessageRecord> = try {
        val url = baseUrl.plus(POST_MSG_PATH)
        val result = requester.post(
            url, mapOf(
                POST_MSG_KEY_DEVICE to Platform.platform,
                POST_MSG_KEY_MSG to message
            )
        )
        decodeBody(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    private inline fun <reified T> decodeBody(result: Result<String>) = if (result.isSuccess) {
        result.getOrNull()?.let {
            val imageMessage = jsonParser.decodeFromString<T>(it)
            Result.success(imageMessage)
        } ?: Result.failure(NullPointerException("message is null"))
    } else Result.failure(
        result.exceptionOrNull() ?: NullPointerException("Result value is null")
    )
}