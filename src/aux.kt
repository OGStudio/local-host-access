package org.opengamestudio

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

    // If arguments do not specify dir, set dir value to current working directory.
    // TODO: contact plt functions?
    return "N/A"
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
