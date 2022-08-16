package com.korilin.kmm.explore

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}