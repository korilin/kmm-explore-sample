package com.korilin.kmm.explore.serve.controller

import com.korilin.kmm.explore.serve.DOWNLOAD_PATH
import com.korilin.kmm.explore.serve.model.DeviceMessage
import com.korilin.kmm.explore.serve.model.RandomImageMessage
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress
import java.util.Date

private val images by lazy {
    ResourceUtils.getFile("classpath:images").listFiles()?.map { it?.name ?: "1.png" } ?: emptyList()
}

private fun getRandomImage(request: ServerHttpRequest) =
    "http:/${request.localAddress}$DOWNLOAD_PATH/${images.random()}"

@RestController
class PostController {

    @PostMapping("/post/msg")
    suspend fun randomDataWithDevice(
        @RequestBody message: DeviceMessage, request: ServerHttpRequest
    ) = RandomImageMessage(
        Date().time, getRandomImage(request), "${message.device}: ${message.msg}"
    )
}