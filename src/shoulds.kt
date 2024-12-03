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
 * 0. Request has been made
 * 1. GET /path
 * 2. POST /list
 * 3. POST /read
 * 4. POST /write
 * 5. Unexpected request
 */
fun shouldReplyOverHTTP(c: Context): Context {
    if (
        c.recentField != "httpRequest"
    ) {
        c.recentField = "none"
        return c
    }

    if (
        c.httpRequest.path == "/path" &&
        c.httpRequest.method == "GET"
    ) {
        c.httpReply = c.dir
        c.recentField = "httpReply"
        return c
    }

    if (
        c.httpRequest.path == "/list" &&
        c.httpRequest.method == "POST"
    ) {
        val files1 = fsListFiles(c.httpRequest.body)
        val files2 = excludeTechFiles(files1)
        c.httpReply = jsonFiles(files2)
        c.recentField = "httpReply"
        return c
    }

    if (
        c.httpRequest.path == "/read" &&
        c.httpRequest.method == "POST"
    ) {
        c.httpReply = fsReadFile(c.httpRequest.body)
        c.recentField = "httpReply"
        return c
    }

    if (
        c.httpRequest.path == "/write" &&
        c.httpRequest.method == "POST"
    ) {
        val d = jsonToPathContents(c.httpRequest.body)
        val path = d["path"] ?: "N/A"
        val contents = d["contents"] ?: "N/A"
        fsWriteFile(path, contents)
        c.httpReply = ""
        c.recentField = "httpReply"
        return c
    }

    // Default reply for unexpected path.
    c.httpReply = "TODO provide help, because requested wrong path: '${c.httpRequest.path}'"
    c.recentField = "httpReply"
    return c
}

/* Set working directory
 *
 * Conditions:
 * 1. Default directory was specified
 * 2. User directory was specified with command line argument
 */
fun shouldResetDir(c: Context): Context {
    if (
        c.recentField == "defaultDir"
    ) {
        c.dir = c.defaultDir
        c.recentField = "dir"
        return c
    }

    if (
        c.recentField == "arguments" &&
        cliDir(c.arguments).length > 0
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
        c.recentField == "httpDefaultPort"
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
