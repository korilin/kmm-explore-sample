package com.korilin.kmm.explore

import com.korilin.kmm.explore.datasource.network.NetRequester
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class KtorNetRequester : NetRequester {

    private val client = HttpClient()

    override suspend fun get(url: String, params: Map<String, Any?>): Result<String> {
        return kotlin.runCatching {
            val urlWithParams = urlWithParams(url, params)
            val response = client.get(urlWithParams)
            response.bodyAsText()
        }
    }

    override suspend fun post(url: String, params: Map<String, Any?>): Result<String> {
        return kotlin.runCatching {
            val response = client.post(url) {
                contentType(ContentType.Application.Json)
                setBody(params)
            }
            response.bodyAsText()
        }
    }
}