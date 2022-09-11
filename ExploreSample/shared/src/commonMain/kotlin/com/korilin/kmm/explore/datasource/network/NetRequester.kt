package com.korilin.kmm.explore.datasource.network

import kotlinx.serialization.KSerializer

interface NetRequester {
    suspend fun get(url: String, params: Map<String, Any?>): Result<String>

    suspend fun <T> post(url: String, params: T, serializer: KSerializer<T>): Result<String>

    fun urlWithParams(url: String, params: Map<String, Any?>): String {
        val urlBuilder = StringBuilder(url.plus("?"))
        for (param in params) {
            urlBuilder.append("${param.key}=${param.value}")
        }
        return urlBuilder.toString()
    }
}