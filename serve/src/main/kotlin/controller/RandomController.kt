package com.korilin.kmm.explore.serve.controller

import com.korilin.kmm.explore.serve.DOWNLOAD_URL
import com.korilin.kmm.explore.serve.model.RandomDeviceData
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Date

private val images by lazy {
    ResourceUtils.getFile("classpath:images").listFiles()?.map { it?.name ?: "1.png" } ?: emptyList()
}

private fun getRandomImage() = "$DOWNLOAD_URL/${images.random()}"

@RestController
@RequestMapping("/random")
class RandomController {

    @GetMapping("/device")
    suspend fun randomDataWithDevice(
        @RequestParam device: String, @RequestParam msg: String
    ) = RandomDeviceData(
        Date().time, getRandomImage(), "$device: $msg"
    )
}