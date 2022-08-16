package com.korilin.kmm.explore.serve.controller

import com.korilin.kmm.explore.serve.model.RandomDeviceData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Date

private val images = arrayOf(
    "https://i.bmp.ovh/imgs/2022/08/16/65c45c3f00ca7251.png",
    "https://s3.bmp.ovh/imgs/2022/08/16/8da9eccf52c3c2c0.jpg",
    "https://s3.bmp.ovh/imgs/2022/08/16/522dcab5df97204a.png",
    "https://s3.bmp.ovh/imgs/2022/08/16/8e181676c40c66d5.jpg",
    "https://s3.bmp.ovh/imgs/2022/08/16/dcfcf4dbb4e4a08a.png"
)

@RestController
@RequestMapping("/random")
class RandomController {

    @GetMapping("/device")
    suspend fun randomDataWithDevice(
        @RequestParam device: String, @RequestParam msg: String
    ) = RandomDeviceData(
        Date().time, images.random(), "$device: $msg"
    )
}