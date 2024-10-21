import org.opengamestudio.*

class NativeApp {
    val greeting: String
        get() {
            return "Hello World! Native"
        }
}

fun main() {
    var c = Context()
    c.didLaunch = true
    println(NativeApp().greeting + " didL: '${c.didLaunch}'")
}
