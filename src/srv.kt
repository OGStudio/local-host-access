package org.opengamestudio
/**
 * This file only exists to work around Ktor suspendable functions,
 * which break simpler reactive approach by SSOTA
 */ 

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
 * Process HTTP request
 */
suspend fun srvProcessHTTPRequest(
    p: Platform,
    call: ApplicationCall
) {
    var req = NetRequest()
    // Path.
    req.path = call.request.uri
    // Method.
    req.method = reqMethod(call.request.httpMethod)
    req.body = call.receive<String>()

    /**/println("ИГР srvPHR method/path/body: '${req.method}'/'${req.path}'/'${req.body}'")

    // The call to `httpRequest` results in synchronous
    // execution of logic that updates `httpReply` indirectly.
    p.ctrl.set("httpRequest", req)
    // Allow CORS.
    call.response.header("Access-Control-Allow-Origin", "*")
    // Send reply.
    call.respondText(p.c.httpReply)
}

/**
 * Run simple HTTP server
 */
fun srvRunHTTPServer(p: Platform) {
    // Prepare HTTP server routing.
    val srv = embeddedServer(CIO, p.c.httpPort) {
        routing {
            get("/{path...}") {
                srvProcessHTTPRequest(p, call)
            }
            options("/{path...}") {
                srvProcessHTTPRequest(p, call)
            }
            post("/{path...}") {
                srvProcessHTTPRequest(p, call)
            }
        }
    }
    // Launch HTTP server if necessary.
    if (p.c.httpLaunch) {
        srv.start(wait = true)
    }
}
