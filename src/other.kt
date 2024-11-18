package org.opengamestudio

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
 * Exclude techincal files
 */
fun excludeTechFiles(files: Array<FSFile>): Array<FSFile> {
    var out = arrayOf<FSFile>()
    for (file in files) {
        if (!isTechFileName(file.name)) {
            out += file
        }
    }
    return out
}

/**
 *
 * Detect if a file is technical
 *
 * Technical files names are:
 * 1. "."
 * 2. ".."
 * 3. ".*"
 */
fun isTechFileName(name: String): Boolean {
    if (
        name == "." ||
        name == ".." ||
        name.startsWith(".")
    ) {
        return true
    }

    return false
}

/**
 * Convert list of files to json format
 */
fun jsonFiles(files: Array<FSFile>): String {
    var lines = arrayOf<String>()
    for (file in files) {
        val type = if (file.isFile) "file" else "dir"
        val ln = "{\"path\":\"${file.name}\",\"type\":\"$type\"}"
        lines += ln
    }
    val out = "[" + lines.joinToString(",") + "]"
    return out
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
