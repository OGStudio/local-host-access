import org.opengamestudio.*

class NativeApp { }

fun main(args: Array<String>) {
    val ctrl = ctxController(Context())
    val p = Platform(Context(), ctrl)
    trgRegisterCommonPlatformFunctions(p)
    trgRegisterCommonShoulds(ctrl)
    trgRegisterCommonSettings(ctrl)

    ctrl.set("arguments", args)
    ctrl.set("didLaunch", true)
    srvRunHTTPServer(p)
}
