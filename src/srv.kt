package org.opengamestudio

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.request.uri

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
    val srv: ApplicationEngine = embeddedServer(CIO, port) {
        routing {
            get("/{path...}") {
                ctrl.set("httpPath", call.request.uri)
                call.respondText(reply)
                //println(call::class.qualifiedName)
            }
        }
    }
    srv.start(wait = true)
}
