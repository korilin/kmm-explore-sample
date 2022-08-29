package com.korilin.kmm.explore

import com.korilin.kmm.explore.datasource.network.NetRequester
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class OkHttpNetRequester: NetRequester {

    private val client = OkHttpClient()

    override suspend fun get(
        url: String,
        params: Map<String, Any?>
    ): Result<String> {
        val urlWithParams = StringBuilder(url.plus("?"))
        for (param in params) {
            urlWithParams.append("${param.key}=${param.value}")
        }
        val request = Request.Builder().url(url).get().build()
        return doRequest(request)
    }

    override suspend fun post(
        url: String,
        params: Map<String, Any?>
    ): Result<String> {
        val json = JSONObject(params).toString()
        val body = json.toRequestBody()
        val request = Request.Builder().url(url).post(body).build()
        return doRequest(request)
    }

    private suspend fun doRequest(request: Request): Result<String> = suspendCoroutine {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                it.resume(Result.failure(e))
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body.toString()
                it.resume(Result.success(body))
            }
        })
    }
}