package org.opengamestudio

class JVMApp { }

fun main() {
    val ctrl = ctxController(Context())
    trgRegisterCommonPlatformFunctions(ctrl)
    trgRegisterCommonShoulds(ctrl)
    trgRegisterCommonSettings(ctrl)
    ctrl.set("didLaunch", true)

    srvRunHTTPServer(8080)
}
