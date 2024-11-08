package org.opengamestudio

//import java.io.*
//import kotlin.io.*

import kotlinx.cinterop.*
import platform.posix.*


import io.ktor.http.*

/**
 * Extract dir from command line arguments or set default value
 */
fun cliDir(args: Array<String>): String {
    for (arg in args) {
        if (arg.startsWith(ARGUMENT_DIR)) {
            val prefix = ARGUMENT_DIR + "="
            val dir = arg.substring(prefix.length)
            return dir
        }
    }
    return ""
}

/**
 * Extract port from command line arguments if present
 */
fun cliPort(args: Array<String>): Int {
    for (arg in args) {
        if (arg.startsWith(ARGUMENT_PORT)) {
            val prefix = ARGUMENT_PORT + "="
            val port = arg.substring(prefix.length).toInt()
            return port
        }
    }

    return 0
}

/**
 * List files of the provided directory
 */
/* JVM:
fun fsListFiles(dir: String)/*: Array<String>*/ {
    val list = File(dir).listFiles()
    for (file in list) {
        println("fsLF: '${file.name}'")
    }
}
*/

@OptIn(ExperimentalForeignApi::class)
fun fsListFiles(path: String)/*: Array<String>*/ {
    // https://gist.github.com/kesslern/743d5a3c07b8cfbd52e78ec5268a1dc8
    val dir = opendir(path)
    if (dir != null) {
        try {
            var item = readdir(dir)
            while (item != null) {
                println("fsLF: '${item.pointed.d_name.toKString()}'")
                item = readdir(dir)
            }
        } finally {
            closedir(dir)
        }
    }
}

/**
 * Convert Ktor specific method enum value to String
 */
fun reqMethod(m: HttpMethod): String {
    if (m == HttpMethod.Post) {
        return "POST"
    }

    return "GET"
}
