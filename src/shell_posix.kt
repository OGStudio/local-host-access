package org.opengamestudio

import kotlinx.cinterop.*
import platform.posix.*

/**
 * Execute arbitrary OS command
 *
 * https://stackoverflow.com/a/57124947
 */
@OptIn(ExperimentalForeignApi::class)
fun shellExec(cmd: String): String {
    val fp = popen(cmd, "r")
    val buf = ByteArray(4096)
    val str = StringBuilder()

    // TODO

    return str.trim().toString()
}
