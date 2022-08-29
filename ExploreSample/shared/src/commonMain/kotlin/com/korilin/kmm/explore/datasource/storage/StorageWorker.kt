package com.korilin.kmm.explore.datasource.storage

interface StorageWorker {
    fun put(key: String, value: Any?)
    fun <T> get(key: String, default: T): T
}