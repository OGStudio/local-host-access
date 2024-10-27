package org.opengamestudio

import io.ktor.server.engine.*;

/**
 * Instances of application dependencies
 */
class Platform(
    /**
     * Copy of current Context state
     */
    var c: Context,
    /**
     * Controller to report logic events
     */
    var ctrl: ctxController,
    /**
     * Keep the latest httpReply value for use by server
     */
    var reply: String = "",
    /**
     * Running (if not null) HTTP server instance
     */
    var server: ApplicationEngine? = null,
)
