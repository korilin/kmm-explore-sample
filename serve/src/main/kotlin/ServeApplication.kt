package com.korilin.kmm.explore.serve

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServeApplication

fun main(args: Array<String>) {
    runApplication<ServeApplication>(*args)
}
