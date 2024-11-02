package org.opengamestudio

/**
 * Application state
 */
data class Context(
    /**
     * Command line arguments
     */
    var arguments: Array<String> = arrayOf<String>(),
    /**
     * String to print to console
     */
    var consoleOutput: String = "",
    /**
     * Default directory if not specified with command line arguments
     */
    var defaultDir: String = "",
    /**
     * The application did finish launching
     */
    var didLaunch: Boolean = false,
    /**
     * Path to local directory to work with
     */
    var dir: String = "",
    /**
     * Default HTTP port if not specified with command line arguments
     */
    var httpDefaultPort: Int = 0,
    /**
     * Launch HTTP server
     */
    var httpLaunch: Boolean = false,
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
        if (name == "arguments") {
            return arguments
        } else if (name == "consoleOutput") {
            return consoleOutput
        } else if (name == "defaultDir") {
            return defaultDir
        } else if (name == "didLaunch") {
            return didLaunch
        } else if (name == "dir") {
            return dir
        } else if (name == "httpDefaultPort") {
            return httpDefaultPort
        } else if (name == "httpLaunch") {
            return httpLaunch
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
        if (name == "arguments") {
            arguments = value as Array<String>
        } else if (name == "consoleOutput") {
            consoleOutput = value as String
        } else if (name == "defaultDir") {
            defaultDir = value as String
        } else if (name == "didLaunch") {
            didLaunch = value as Boolean
        } else if (name == "dir") {
            dir = value as String
        } else if (name == "httpDefaultPort") {
            httpDefaultPort = value as Int
        } else if (name == "httpLaunch") {
            httpLaunch = value as Boolean
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
