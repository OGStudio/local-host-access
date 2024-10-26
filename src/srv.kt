package org.opengamestudio

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*

//
//suspend fun doReply(call: ApplicationCall) { }
//


/**
 * Run simple HTTP server
 */
fun srvRunHTTPServer(
    ctrl: ctxController,
    port: Int
) {
    // Keep a copy of HTTP reply.
    var reply = ""
    ctrl.registerFieldCallback("httpReply", { c ->
        reply = (c as Context).httpReply
    })

    // Run HTTP server.
    embeddedServer(CIO, port) {
        routing {
            get("/{path?}") {
                //doReply(call)
                val path = call.parameters["path"] ?: ""
                ctrl.set("path", path)
                call.respondText(reply)
            }
        }
    }.start(wait = true)
}
