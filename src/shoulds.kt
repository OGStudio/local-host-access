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
