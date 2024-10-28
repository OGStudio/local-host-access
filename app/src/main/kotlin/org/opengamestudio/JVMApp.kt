package org.opengamestudio

class JVMApp { }

fun main(args: Array<String>) {
    for (arg in args) {
        println("arg: '$arg'")
    }

    val ctrl = ctxController(Context())
    val p = Platform(Context(), ctrl)
    trgRegisterCommonPlatformFunctions(p)
    trgRegisterCommonShoulds(ctrl)
    trgRegisterCommonSettings(ctrl)

    ctrl.set("didLaunch", true)
    srvRunHTTPServer(p)
}
