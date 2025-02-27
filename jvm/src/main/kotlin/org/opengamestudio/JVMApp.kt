package org.opengamestudio

class JVMApp { }

fun main(args: Array<String>) {
    val ctrl = CLDController(Context())
    val p = Platform(Context(), ctrl)
    trgRegisterCommonPlatformFunctions(p)
    trgRegisterCommonShoulds(ctrl)
    trgRegisterCommonSettings(ctrl)

    ctrl.set("arguments", args)
    ctrl.set("didLaunch", true)
    srvRunHTTPServer(p)
}
