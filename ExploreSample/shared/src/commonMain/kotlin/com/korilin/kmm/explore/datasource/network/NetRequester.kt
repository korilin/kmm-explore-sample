package com.korilin.kmm.explore.datasource.network

interface NetRequester {
    suspend fun get(url: String, params: Map<String, Any?>): Result<String>

    suspend fun post(url: String, params: Map<String, Any?>): Result<String>

    fun urlWithParams(url: String, params: Map<String, Any?>): String {
        val urlBuilder = StringBuilder(url.plus("?"))
        for (param in params) {
            urlBuilder.append("${param.key}=${param.value}")
        }
        return urlBuilder.toString()
    }
}