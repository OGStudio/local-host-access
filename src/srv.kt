package org.opengamestudio

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.request.httpMethod
import io.ktor.server.request.receive
import io.ktor.server.request.uri

/**
 * Run simple HTTP server
 */
fun srvRunHTTPServer(p: Platform) {
    // Prepare HTTP server routing.
    val srv: ApplicationEngine = embeddedServer(CIO, p.c.httpPort) {
        routing {
            get("/{path...}") {
                val url = call.request.uri
                val method = call.request.httpMethod
                val body = ""

                /**/println("ИГР GET url/method/body: '$url'/'$method'/'$body'")

                // The call to `httpPath` results in synchronous
                // execution of logic that updates `p.c.httpReply`
                // indirectly.
                p.ctrl.set("httpPath", call.request.uri)
                call.respondText(p.c.httpReply)
            }

            post("/{path...}") {
                val url = call.request.uri
                val method = call.request.httpMethod
                val body = call.receive<String>()
                /**/println("ИГР POST url/method/body: '$url'/'$method'/'$body'")

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
