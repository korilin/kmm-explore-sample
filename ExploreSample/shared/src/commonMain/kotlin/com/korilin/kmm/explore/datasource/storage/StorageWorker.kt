package com.korilin.kmm.explore.datasource.storage

expect interface StorageWorker {
    fun put(key: String, value: Any?)
    fun <T> get(key: String, default: T): T
}