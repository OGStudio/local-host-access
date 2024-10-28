package org.opengamestudio

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.request.uri

/* Print to console
 *
 * Conditions:
 * 1. Application did launch
 */
fun pltPrintToConsole(
    p: Platform
) {
    if (
        p.c.recentField == "didLaunch"
    ) {
        println("Local Host Access, version ?")
    }
}
