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
fun srvRunHTTPServer(p: Platform) {
    // Prepare HTTP server routing.
    val srv: ApplicationEngine = embeddedServer(CIO, p.c.httpPort) {
        routing {
            get("/{path...}") {
                // The call to `httpPath` results in synchronous
                // execution of logic that updates `p.c.httpReply`
                // indirectly.
                p.ctrl.set("httpPath", call.request.uri)
                call.respondText(p.c.httpReply)
            }
        }
    }
    // Launch HTTP server if necessary.
    if (
        p.c.httpLaunch
    ) {
        srv.start(wait = true)
    }
}
