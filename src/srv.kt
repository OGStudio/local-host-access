package org.opengamestudio

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*

var globalCall: ApplicationCall? = null

suspend fun doReply() {
    val path = globalCall?.parameters["path"]
    globalCall?.respondText("Hello, World!. Path: '$path'")
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
                globalCall = call
                
                doReply()
                //val path = this.call.parameters["path"]
                //call.respondText("Hello, World!. Path: '$path'")
            }
        }
    }.start(wait = true)
}
