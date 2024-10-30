package org.opengamestudio

/**
 * Register platform functions relevant to all targets
 */
fun trgRegisterCommonPlatformFunctions(p: Platform) {
    p.ctrl.registerCallback({ cc: ctxContext ->
        val c = cc as Context
        p.c = c
        pltPrintToConsole(p)
    })
}

/**
 * Register settings relevant to all targets
 */
fun trgRegisterCommonSettings(ctrl: ctxController) {
    ctrl.set("httpDefaultPort", 8080)
}

/**
 * Register behaviour relevant to all targets
 */
fun trgRegisterCommonShoulds(ctrl: ctxController) {
    arrayOf(
        ::shouldLaunchHTTPServer,
        ::shouldPrintToConsole,
        ::shouldReplyOverHTTP,
        ::shouldResetHTTPPort,
    ).forEach { f ->
        ctrl.registerFunction { c -> f(c as Context) }
    }
}
