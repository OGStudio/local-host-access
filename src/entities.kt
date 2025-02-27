package org.opengamestudio

// Application state context
data class Context(
    // Command line arguments
    var arguments: Array<String> = arrayOf(),
    var consoleOutput: String = "",
    var defaultDir: String = "",
    var didLaunch: Boolean = false,
    var dir: String = "",
    var httpDefaultPort: Int = 0,
    var httpLaunch: Boolean = false,
    var httpPort: Int = 0,
    var httpReply: String = "",
    var httpRequest: NetRequest = NetRequest(),
    override var recentField: String = "",
): CLDContext {
    override fun <T> field(name: String): T {
        if (name == "arguments") {
            return arguments as T
        } else if (name == "consoleOutput") {
            return consoleOutput as T
        } else if (name == "defaultDir") {
            return defaultDir as T
        } else if (name == "didLaunch") {
            return didLaunch as T
        } else if (name == "dir") {
            return dir as T
        } else if (name == "httpDefaultPort") {
            return httpDefaultPort as T
        } else if (name == "httpLaunch") {
            return httpLaunch as T
        } else if (name == "httpPort") {
            return httpPort as T
        } else if (name == "httpReply") {
            return httpReply as T
        } else if (name == "httpRequest") {
            return httpRequest as T
        }
        return "unknown-field-name" as T
    }

    override fun selfCopy(): CLDContext {
        return this.copy()
    }

    override fun setField(
        name: String,
        value: Any?
    ) {
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
        } else if (name == "httpPort") {
            httpPort = value as Int
        } else if (name == "httpReply") {
            httpReply = value as String
        } else if (name == "httpRequest") {
            httpRequest = value as NetRequest
        }
    }
}

// File system item representation
data class FSFile(
    var isDirectory: Boolean = false,
    var isFile: Boolean = false,
    var name: String = "",
) {}

// Client request representation
data class NetRequest(
    var body: String = "",
    var method: String = "",
    var path: String = "",
) {}
