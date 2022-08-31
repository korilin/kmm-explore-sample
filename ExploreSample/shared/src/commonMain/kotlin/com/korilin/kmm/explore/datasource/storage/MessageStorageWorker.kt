package com.korilin.kmm.explore.datasource.storage

import com.ctrip.flight.mmkv.defaultMMKV
import com.korilin.kmm.explore.model.ImageMessageRecord
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


object MessageStorageWorker {
    private val mmkv = defaultMMKV()
    private const val storageKey = "MessageStorage"

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = false
    }

    private val cache = mutableMapOf<Long, ImageMessageRecord>()

    private suspend fun <T> opt(block: () -> T) = suspendCoroutine {
        val result = block.invoke()
        val value = json.encodeToString(cache)
        mmkv[storageKey] = value
        it.resume(result)
    }

    suspend fun insertMessage(messageRecord: ImageMessageRecord) = opt {
        cache[messageRecord.time] = messageRecord
    }

    suspend fun initMessages() = opt {
        val mapJson = mmkv.takeString(storageKey, "{}")
        val messages = json.decodeFromString(
            MapSerializer(
                Long.serializer(),
                ImageMessageRecord.serializer()
            ), mapJson
        )
        cache.clear()
        cache.putAll(messages)
    }

    suspend fun queryAllMessages() = opt {
        cache.toList().map { it.second }.sortedBy { it.time }
    }

    suspend fun removeMessageByTime(time: Long) = opt {
        cache.remove(time)
    }
}