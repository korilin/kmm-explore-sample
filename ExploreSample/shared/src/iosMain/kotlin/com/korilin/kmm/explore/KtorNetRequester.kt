package com.korilin.kmm.explore

import com.korilin.kmm.explore.datasource.network.NetRequester
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class KtorNetRequester : NetRequester {

    private val client = HttpClient()

    override suspend fun get(url: String, params: Map<String, Any?>): Result<String> {
        return kotlin.runCatching {
            val urlWithParams = urlWithParams(url, params)
            val response = client.get(urlWithParams)
            response.bodyAsText()
        }
    }

    override suspend fun <T> post(
        url: String,
        params: T,
        serializer: KSerializer<T>
    ): Result<String> {
        return kotlin.runCatching {
            val response = client.post(url) {
                contentType(ContentType.Application.Json)
                val body = Json.encodeToString(serializer, params)
                setBody(body)
            }
            response.bodyAsText()
        }
    }
}