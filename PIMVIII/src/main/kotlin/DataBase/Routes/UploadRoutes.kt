package com.unip.DataBase.Routes

import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.http.content.streamProvider
import io.ktor.server.application.Application
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import java.io.File
import kotlin.random.Random

fun Application.uploadRoutes(){
    routing{
        route("/upload"){
            post {
                val multipart = call.receiveMultipart()

                var videoFile: File? = null
                var thumbnailFile: File? = null

                multipart.forEachPart { part ->
                    when (part) {
                        is PartData.FileItem -> {
                            val name = part.originalFileName!!

                            if (name.endsWith(".mp4") || name.endsWith(".mp3")) {
                                val file = File("uploads/videos/${Random.nextInt()}_$name")
                                part.streamProvider().use { input ->
                                    file.outputStream().buffered().use { output ->
                                        input.copyTo(output)
                                    }
                                }
                                videoFile = file
                            }

                            if (name.endsWith(".jpg") || name.endsWith(".png")) {
                                val file = File("uploads/thumbnails/${Random.nextInt()}_$name")
                                part.streamProvider().use { input ->
                                    file.outputStream().buffered().use { output ->
                                        input.copyTo(output)
                                    }
                                }
                                thumbnailFile = file
                            }
                        }
                        else -> {}
                    }
                    part.dispose()
                }

                call.respond(
                    HttpStatusCode.OK,
                    mapOf(
                        "video" to videoFile?.path,
                        "thumbnail" to thumbnailFile?.path
                    )
                )
            }
        }
    }
}