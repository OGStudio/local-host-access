package org.opengamestudio

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*

class JVMApp { }

fun main() {
    val ctrl = ctxController(Context())
    targetRegisterCommonPlatformFunctions(ctrl)
    targetRegisterCommonShoulds(ctrl)
    targetRegisterCommonSettings(ctrl)
    ctrl.set("didLaunch", true)

    embeddedServer(CIO, 8080) {
        routing {
            get("/") {
                call.respondText("Hello, World!")
            }
        }
    }.start(wait = true)
}
