package org.opengamestudio

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*

suspend fun doReply(call: ApplicationCall) {
    val path = call.parameters["path"]
    call.respondText("Hello, World!. Path: '$path'")
}

/**
 * Run simple HTTP server
 */
fun srvRunHTTPServer(
    port: Int
) {
    embeddedServer(CIO, port) {
        routing {
            get("/{path?}") {
                doReply(call)
            }
        }
    }.start(wait = true)
}
