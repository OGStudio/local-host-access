import org.opengamestudio.*

class NativeApp { }

fun main() {
    val ctrl = ctxController(Context())
    trgRegisterCommonPlatformFunctions(ctrl)
    trgRegisterCommonShoulds(ctrl)
    trgRegisterCommonSettings(ctrl)
    ctrl.set("didLaunch", true)

    srvRunHTTPServer(ctrl, 8080)
}
