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
)
