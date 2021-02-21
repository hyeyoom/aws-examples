package com.github.hyeyoom.aws.s3.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream

private val log = KotlinLogging.logger {}

interface FileStorageService {
}

@Service
class S3FileStorageService(
    private val s3: AmazonS3
) : FileStorageService {

    fun upload(key: String, data: ByteArray, contentType: String?) {
        val metadata = ObjectMetadata()
        metadata.contentLength = data.size.toLong()
        metadata.contentType = contentType
        ByteArrayInputStream(data).use {
            try {
                val putObjectRequest = PutObjectRequest("hyeyoom-s3-tutorial", key, it, metadata)
                s3.putObject(putObjectRequest)
            } catch (e: Exception) {
                log.error(e.message, e)
            }
        }
    }
}