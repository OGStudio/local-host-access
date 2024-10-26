package org.opengamestudio

/* Should reply over HTTP
 *
 * Conditions:
 * 1. Arbitrary path has been requested
 */
fun shouldReplyOverHTTP(c: Context): Context {
    if (
        c.recentField == "path"
    ) {
        c.httpReply = "This came from shouldROH. The path was: '${c.path}'"
        c.recentField = "httpReply"
        return c
    }

    c.recentField = "none"
    return c
}
