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
     * Reply to deliver over HTTP to a client
     */
    var httpReply: String = "",
    /**
     * Path requested by a client
     */
    var path: String = "",
    override var recentField: String = "",
): ctxContext {
    override fun field(name: String): Any {
        if (name == "didLaunch") {
            return didLaunch
        } else if (name == "httpReply") {
            return httpReply
        } else if (name == "path") {
            return path
        }
        return "unknown-field-name"
    }

    override fun selfCopy(): ctxContext {
        return this.copy()
    }

    override fun setField(name: String, value: Any) {
        if (name == "didLaunch") {
            didLaunch = value as Boolean
        } else if (name == "httpReply") {
            httpReply = value as String
        } else if (name == "path") {
            path = value as String
        }
    }
}

/**
 * Create instance of Context
 */
fun createContext(): Context {
    return Context()
}
