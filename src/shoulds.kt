package org.opengamestudio

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
