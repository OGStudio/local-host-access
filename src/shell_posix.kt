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

    if (fp == null) {
        return "ERR shellExec cmd: '$cmd'"
    }

    var scan = fgets(buf.refTo(0), buf.size, fp)
    while (scan != null) {
        str.append(scan?.toKString())
        scan = fgets(buf.refTo(0), buf.size, fp)
    }

    pclose(fp)
    return str.trim().toString()
}
