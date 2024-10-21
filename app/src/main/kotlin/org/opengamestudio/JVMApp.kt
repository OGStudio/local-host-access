package org.opengamestudio

class JVMApp {
    val greeting: String
        get() {
            return "Hello World! JVM here"
        }
}

fun main() {
    var c = Context()
    c.didLaunch = true
    println(JVMApp().greeting + " didL: '${c.didLaunch}'")
}
