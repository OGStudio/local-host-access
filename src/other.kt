package org.opengamestudio

import io.ktor.http.*
import kotlin.io.encoding.*

/**
 * Get absolute path for file system operations
 *
 * Conditions:
 * 1. Requested path is empty
 * 2. Requested path is not empty
 */
fun absolutePath(
    workingDir: String,
    requestedPath: String
): String {
    if (requestedPath.isEmpty()) {
        return workingDir
    }
    return "$workingDir/$requestedPath"
}

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
 * Extract file type from `stat` command output
 */
fun cliStatIsDirectory(output: String): Boolean {
    val parts = output.split(" ")
    if (
        parts.size > 2 &&
        parts[2].startsWith("d")
    ) {
        return true
    }
    return false
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
 * Convert JSON to dictionary with `path` and `contents`
 *
 * Expected JSON string: `{"path": "abc", "contents": "def"}`
 */
@OptIn(ExperimentalEncodingApi::class)
fun jsonToPathContents(str: String): Map<String, String> {
    var d = mutableMapOf<String, String>()
    val parts = str.split("\"")
    if (parts.size == 9) {
        d["path"] = parts[3]
        val contentsB64 = parts[7]
        d["contents"] = Base64.Default.decode(contentsB64).decodeToString()
    }
    return d
}

/**
 * Convert Ktor specific method enum value to String
 */
fun reqMethod(m: HttpMethod): String {
    if (m == HttpMethod.Options) {
        return "OPTIONS"
    } else if (m == HttpMethod.Post) {
        return "POST"
    }

    return "GET"
}
