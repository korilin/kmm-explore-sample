package com.korilin.kmm.explore.serve.controller

import com.korilin.kmm.explore.serve.ControllerPath
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ZeroCopyHttpOutputMessage
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

private const val IMAGE_PATH = "images"

@RestController
@RequestMapping(ControllerPath.DOWNLOAD)
class DownloadController {

    @GetMapping("/img/{filename}")
    fun downloadImg(@PathVariable filename: String, response: ServerHttpResponse): Mono<Void> {
        val zeroCopyResponse = response as ZeroCopyHttpOutputMessage
        zeroCopyResponse.headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=$filename")
        zeroCopyResponse.headers.contentType = MediaType.IMAGE_PNG
        val image = ClassPathResource("$IMAGE_PATH/$filename").file
        return zeroCopyResponse.writeWith(image, 0, image.length())
    }
}