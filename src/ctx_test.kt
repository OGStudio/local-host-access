package org.opengamestudio

/**
 * Context sample. Only used for testing
 */
data class exampleContext(
    var didLaunch: Boolean = false,
    var host: String = "",
    override var recentField: String = "",
): ctxContext {
    override fun field(name: String): Any {
        if (name == "didLaunch") {
            return didLaunch
        } else if (name == "host") {
            return host
        }
        return "unknown-field-name"
    }

    override fun selfCopy(): ctxContext {
        return this.copy()
    }

    override fun setField(name: String, value: Any) {
        if (name == "didLaunch") {
            didLaunch = value as Boolean
        } else if (name == "host") {
            host = value as String
        }
    }
}

/**
 * Validate field access by the name
 */
fun test_exampleContext_field(): Boolean {
    var c = exampleContext()
    c.host = "abc"
    return c.host == c.field("host")
}

/**
 * Validate `selfCoppy()` returns a new instance of context
 */
fun test_exampleContext_selfCopy(): Boolean {
    var c1 = exampleContext()
    c1.host = "abc"
    var c2 = c1.selfCopy() as exampleContext
    c2.host = "123"
    return c1.host == "abc"
}

/**
 * Validate changing field value by name
 */
fun test_exampleContext_setField(): Boolean {
    var c = exampleContext()
    c.host = "abc"
    c.setField("host", "123")
    return c.host == "123"
}

/**
 * Validate `executeFunctions()` and `set()`
 */
fun test_ctxController_executeFunctions_set(): Boolean {
    var c = exampleContext()
    val ctrl = ctxController(c)
    // Disable the execution of `executeFunctions()`.
    ctrl.isProcessingQueue = true
    ctrl.set("host", "123")

    fun hostToDidLaunch(c: exampleContext): exampleContext {
        if (c.recentField == "host") {
            c.didLaunch = true
            c.recentField = "didLaunch"
            return c
        }
        c.recentField = "none"
        return c
    }
    ctrl.registerFunction({ c: ctxContext ->
        hostToDidLaunch(c as exampleContext)
    })

    // Apply `host` value.
    ctrl.executeFunctions()
    // Apply `didLaunch` value.
    ctrl.executeFunctions()
    return c.host == "123" &&
        c.didLaunch == true
}

/**
 * Validate `processQueue()`
 */
fun test_ctxController_processQueue(): Boolean {
    var c = exampleContext()
    val ctrl = ctxController(c)

    fun hostToDidLaunch(c: exampleContext): exampleContext {
        if (c.recentField == "host") {
            c.didLaunch = true
            c.recentField = "didLaunch"
            return c
        }
        c.recentField = "none"
        return c
    }

    ctrl.registerFunction({ c: ctxContext ->
        hostToDidLaunch(c as exampleContext)
    })
    ctrl.set("host", "123")
    return c.didLaunch == true
}

/**
 * Validate `registerFieldCallback()` if an expected field was changed
 */
fun test_ctxController_registerFieldCallback_match(): Boolean {
    var c = exampleContext()
    c.host = "123"
    c.recentField = "host"
    var callbackHost = ""

    val ctrl = ctxController(c)
    ctrl.registerFieldCallback("host", { c: ctxContext ->
      callbackHost = (c as exampleContext).host
    })
    ctrl.reportContext()

    return c.host == callbackHost
}

/**
 * Validate `registerFieldCallback()` if an unexpected field was changed, i.e.,
 * callback should not be called
 */
fun test_ctxController_registerFieldCallback_mismatch(): Boolean {
    var c = exampleContext()
    c.host = "123"
    c.recentField = "host"
    var callbackHost = ""

    val ctrl = ctxController(c)
    ctrl.registerFieldCallback("didLaunch", { c: ctxContext ->
        callbackHost = (c as exampleContext).host
    })
    ctrl.reportContext()

    return callbackHost == ""
}
