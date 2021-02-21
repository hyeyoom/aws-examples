package com.github.hyeyoom.aws.s3.controller

import com.github.hyeyoom.aws.s3.service.S3FileStorageService
import org.springframework.util.FileCopyUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File

@RequestMapping("/files")
@RestController
class UploadController(
    private val service: S3FileStorageService
) {

    @GetMapping
    fun test(): String {
        val file = File("C:\\Users\\highn\\찌오2.jpg")
        service.upload("test-upload", FileCopyUtils.copyToByteArray(file), "")
        return "bye"
    }
}