package org.opengamestudio

import io.ktor.server.engine.*;

/**
 * Instances of application dependencies
 */
class Platform(
    /**
     * Running (if not null) HTTP server instance
     */
    var server: ApplicationEngine? = null,
)
