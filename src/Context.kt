package org.opengamestudio

/**
 * Application state
 */
data class Context(
    /**
     * The application did finish launching
     */
    var didLaunch: Boolean = false,
    /**
     * Path to local directory to work with
     */
    var dir: String = "",
    /**
     * Path requested by a client
     */
    var httpPath: String = "",
    /**
     * HTTP port to listen for client requests
     */
    var httpPort: Int = 0,
    /**
     * Reply to deliver over HTTP to a client
     */
    var httpReply: String = "",
    override var recentField: String = "",
): ctxContext {
    override fun field(name: String): Any {
        if (name == "didLaunch") {
            return didLaunch
        } else if (name == "dir") {
            return dir
        } else if (name == "httpPath") {
            return httpPath
        } else if (name == "httpPort") {
            return httpPort
        } else if (name == "httpReply") {
            return httpReply
        }
        return "unknown-field-name"
    }

    override fun selfCopy(): ctxContext {
        return this.copy()
    }

    override fun setField(name: String, value: Any) {
        if (name == "didLaunch") {
            didLaunch = value as Boolean
        } else if (name == "dir") {
            dir = value as String
        } else if (name == "httpPath") {
            httpPath = value as String
        } else if (name == "httpPort") {
            httpPort = value as Int
        } else if (name == "httpReply") {
            httpReply = value as String
        }
    }
}

/**
 * Create instance of Context
 */
fun createContext(): Context {
    return Context()
}
