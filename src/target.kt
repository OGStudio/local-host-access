package org.opengamestudio

/**
 * Register platform functions relevant to all targets
 */
fun targetRegisterCommonPlatformFunctions(
    ctrl: ctxController
) {
    ctrl.registerCallback({ cc: ctxContext ->
        val c = cc as Context
        printToConsole(c)
    })
}

/**
 * Register settings relevant to all targets
 */
fun targetRegisterCommonSettings(
    ctrl: ctxController
) {
    //ctrl.set(...
}

/**
 * Register behaviour relevant to all targets
 */
fun targetRegisterCommonShoulds(
    ctrl: ctxController
) {
  /*
    arrayOf(
        //::should??,
    ).forEach { f ->
        ctrl.registerFunction { c -> f(c as Context) }
    }
    */
}
