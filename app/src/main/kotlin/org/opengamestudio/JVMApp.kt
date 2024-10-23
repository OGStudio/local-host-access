package org.opengamestudio

class JVMApp { }

fun main() {
    val ctrl = ctxController(Context())
    targetRegisterCommonPlatformFunctions(ctrl)
    targetRegisterCommonShoulds(ctrl)
    targetRegisterCommonSettings(ctrl)
    ctrl.set("didLaunch", true)
}
