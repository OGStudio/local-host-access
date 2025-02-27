package org.opengamestudio

/**
 * Register platform functions relevant to all targets
 */
fun trgRegisterCommonPlatformFunctions(p: Platform) {
    p.ctrl.registerCallback({ cc ->
        val c = cc as Context
        p.c = c
        pltDebug(p)
        pltPrintToConsole(p)
    })
}

/**
 * Register settings relevant to all targets
 */
fun trgRegisterCommonSettings(ctrl: CLDController) {
    ctrl.set("defaultDir", ".")
    ctrl.set("httpDefaultPort", 8080)
}

/**
 * Register behaviour relevant to all targets
 */
fun trgRegisterCommonShoulds(ctrl: CLDController) {
    arrayOf(
        ::shouldLaunchHTTPServer,
        ::shouldPrintToConsole,
        ::shouldReplyOverHTTP,
        ::shouldResetDir,
        ::shouldResetHTTPPort,
    ).forEach { f ->
        ctrl.registerFunction { c -> f(c as Context) }
    }
}
