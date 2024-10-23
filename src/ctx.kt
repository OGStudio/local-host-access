package org.opengamestudio

/**
 * Mandatory interface for all context data classes
 */
interface ctxContext {
    /**
     * Field that has been changed last
     *
     * Allows shoulds/handlers/functions to react to updates of the
     * relevant Context fields
     */
    var recentField: String

    /**
     * Get field value by name
     */
    fun field(name: String): Any

    /**
     * Create a copy of the derived object
     *
     * `ctxController` requires this to manage a queue of context states
     */
    fun selfCopy(): ctxContext

    /**
     * Set field value by the name
     */
    fun setField(name: String, value: Any)
}

/**
 * Manages context by registered functions
 */
class ctxController(
    var context: ctxContext
) {
    internal var callbacks = mutableListOf<(c: ctxContext) -> Unit>()
    internal var functions = mutableListOf<(c: ctxContext) -> ctxContext>()
    var isProcessingQueue = false
    internal var queue = mutableListOf<ctxContext>()

    fun executeFunctions() {
        val c = queue.removeFirst()
        context.recentField = c.recentField
        context.setField(c.recentField, c.field(c.recentField))
      
        for (func in functions) {
            val ctx = func(context.selfCopy())
            if (ctx.recentField != "none") {
                queue.add(ctx)
            }
        }
      
        reportContext()
    }

    fun processQueue() {
        // Prevent recursion.
        if (isProcessingQueue) {
            return
        }
      
        isProcessingQueue = true
      
        while (queue.size > 0) {
            executeFunctions()
        }
      
        isProcessingQueue = false
    }

    fun registerCallback(cb: (c: ctxContext) -> Unit) {
        callbacks.add(cb)
    }

    fun registerFieldCallback(
        fieldName: String,
        cb: (ctxContext) -> Unit
    ) {
        callbacks.add({c: ctxContext -> if (c.recentField == fieldName) cb(c) })
    }

    fun registerFunction(f: (ctxContext) -> ctxContext) {
        functions.add(f)
    }

    fun reportContext() {
        for (cb in callbacks) {
            cb(context)
        }
    }

    fun set(fieldName: String, value: Any) {
        var c = context.selfCopy()
        c.setField(fieldName, value)
        c.recentField = fieldName
        queue.add(c)
        processQueue()
    }
}
