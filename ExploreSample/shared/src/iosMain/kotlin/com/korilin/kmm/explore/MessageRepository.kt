package com.korilin.kmm.explore

import com.korilin.kmm.explore.datasource.network.MessagePoster
import com.korilin.kmm.explore.datasource.storage.MessageStorageWorker
import com.korilin.kmm.explore.model.ImageMessageRecord

object MessageRepository {

    suspend fun initData() = MessageStorageWorker.initMessages()

    suspend fun queryAllMessages() = MessageStorageWorker.queryAllMessages()

    suspend fun postMessage(message: String): ImageMessageRecord {
        val result = MessagePoster.postMessage(message)
        return result.getOrThrow()
    }

    suspend fun insertRecord(record: ImageMessageRecord) {
        MessageStorageWorker.insertMessage(record)
    }

    suspend fun removeRecord(record: ImageMessageRecord) {
        MessageStorageWorker.removeMessageByTime(record.time)
    }
}