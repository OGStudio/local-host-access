package org.opengamestudio

/**
 * Whole application state
 */
data class Context(
    /**
     * The application did finish launching
     */
    var didLaunch: Boolean = false,
    override var recentField: String = "",
): ctxContext {
    override fun field(name: String): Any {
        if (name == "didLaunch") {
            return didLaunch
        }
        return "unknown-field-name"
    }

    override fun selfCopy(): ctxContext {
        return this.copy()
    }

    override fun setField(name: String, value: Any) {
        if (name == "didLaunch") {
            didLaunch = value as Boolean
        }
    }
}

/**
 * Create instance of Context
 */
fun createContext(): Context {
    return Context()
}
