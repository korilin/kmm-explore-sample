package com.korilin.kmm.explore.datasource.network

interface NetRequester {
    suspend fun get(url: String, params: Map<String, Any?>): Result<String>
    suspend fun post(url: String, params: Map<String, Any?>): Result<String>
}