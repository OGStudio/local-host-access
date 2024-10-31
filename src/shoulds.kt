package org.opengamestudio

/* Should launch HTTP server
 *
 * Conditions:
 * 1. Command line arguments are in
 */
fun shouldLaunchHTTPServer(c: Context): Context {
    if (
        c.recentField == "arguments" &&
        !(
            c.arguments.contains(ARGUMENT_HELP) ||
            c.arguments.contains(ARGUMENT_VERSION)
        )
    ) {
        c.httpLaunch = true
        c.recentField = "httpLaunch"
        return c
    }

    c.recentField = "none"
    return c
}

/* Should print to console
 *
 * Conditions:
 * 1. Help was requested
 * 2. Version was requested
 */
fun shouldPrintToConsole(c: Context): Context {
    if (
        c.recentField == "arguments" &&
        c.arguments.contains(ARGUMENT_HELP)
    ) {
        c.consoleOutput = "Usage: TODO provide help"
        c.recentField = "consoleOutput"
        return c
    }

    if (
        c.recentField == "arguments" &&
        c.arguments.contains(ARGUMENT_VERSION)
    ) {
        c.consoleOutput = "local-host-access $APP_VERSION"
        c.recentField = "consoleOutput"
        return c
    }

    c.recentField = "none"
    return c
}

/* Should reply over HTTP
 *
 * Conditions:
 * 1. Arbitrary path has been requested
 */
fun shouldReplyOverHTTP(c: Context): Context {
    if (
        c.recentField == "httpPath"
    ) {
        c.httpReply = "This came from shouldROH. The path was: '${c.httpPath}'"
        c.recentField = "httpReply"
        return c
    }

    c.recentField = "none"
    return c
}

/* Set working directory
 *
 * Conditions:
 * 1. Command line arguments are in
 */
fun shouldResetDir(c: Context): Context {
    if (
        c.recentField == "arguments"
    ) {
        c.dir = cliDir(c.arguments)
        c.recentField = "dir"
        return c
    }

    c.recentField = "none"
    return c
}

/* Should set HTTP port to use for listening
 *
 * Conditions:
 * 1. Default port was specified
 * 2. User port was specified with command line argument
 */
fun shouldResetHTTPPort(c: Context): Context {
    if (
        c.recentField == "httpDefaultPath"
    ) {
        c.httpPort = c.httpDefaultPort
        c.recentField = "httpPort"
        return c
    }

    if (
        c.recentField == "arguments" &&
        cliPort(c.arguments) > 0
    ) {
        c.httpPort = cliPort(c.arguments)
        c.recentField = "httpPort"
        return c
    }

    c.recentField = "none"
    return c
}
