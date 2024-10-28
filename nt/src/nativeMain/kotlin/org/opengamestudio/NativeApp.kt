import org.opengamestudio.*

class NativeApp { }

fun main() {
    val ctrl = ctxController(Context())
    val p = Platform(Context(), ctrl)
    trgRegisterCommonPlatformFunctions(p)
    trgRegisterCommonShoulds(ctrl)
    trgRegisterCommonSettings(ctrl)

    ctrl.set("didLaunch", true)
    srvRunHTTPServer(p)
}
