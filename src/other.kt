package org.opengamestudio

import io.ktor.http.*

/**
 * Extract dir from command line arguments or set default value
 */
fun cliDir(args: Array<String>): String {
    for (arg in args) {
        if (arg.startsWith(ARGUMENT_DIR)) {
            val prefix = ARGUMENT_DIR + "="
            val dir = arg.substring(prefix.length)
            return dir
        }
    }
    return ""
}

/**
 * Extract port from command line arguments if present
 */
fun cliPort(args: Array<String>): Int {
    for (arg in args) {
        if (arg.startsWith(ARGUMENT_PORT)) {
            val prefix = ARGUMENT_PORT + "="
            val port = arg.substring(prefix.length).toInt()
            return port
        }
    }

    return 0
}

/**
 * Convert Ktor specific method enum value to String
 */
fun reqMethod(m: HttpMethod): String {
    if (m == HttpMethod.Post) {
        return "POST"
    }

    return "GET"
}
