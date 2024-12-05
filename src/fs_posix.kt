package org.opengamestudio

import kotlinx.cinterop.*
import platform.posix.*

/**
 * List files of the provided directory
 *
 * POSIX implementation
 * https://gist.github.com/kesslern/743d5a3c07b8cfbd52e78ec5268a1dc8
 */
@OptIn(ExperimentalForeignApi::class)
fun fsListFiles(path: String): Array<FSFile> {
    var items = arrayOf<FSFile>()
    val dir = opendir(path)
    if (dir != null) {
        try {
            var entry = platform.posix.readdir(dir)
            while (entry != null) {
                val fileName = entry.pointed.d_name.toKString()
                var item =
                    FSFile(
                        false,
                        false,
                        fileName,
                    )
                if (entry.pointed.d_type == DT_REG.toUByte()) {
                    // File.
                    item.isFile = true
                } else if (entry.pointed.d_type == DT_DIR.toUByte()) {
                    // Directory.
                    item.isDirectory = true
                } else {
                    // Unknown. Use `stat -L` to identify file type.
                    val cmd = "stat -L $path/$fileName"
                    val result = shellExec(cmd)
                    if (cliStatIsDirectory(result)) {
                        item.isDirectory = true
                    } else {
                        item.isFile = true
                    }
                }

                items += item
                entry = readdir(dir)
            }
        } finally {
            closedir(dir)
        }
    }
    return items
}

/**
 * Read text file
 *
 * POSIX implementation
 * https://www.nequalsonelifestyle.com/2020/11/16/kotlin-native-file-io/
 * https://stackoverflow.com/a/57124947
 */
@OptIn(ExperimentalForeignApi::class)
fun fsReadFile(path: String): String {
    val fp = fopen(path, "r")
    val buf = ByteArray(4096)
    val str = StringBuilder()

    if (fp == null) {
        return "ERR fsReadFile path: '$path'"
    }

    var line = fgets(buf.refTo(0), buf.size, fp)
    while (line != null) {
        str.append(line?.toKString())
        line = fgets(buf.refTo(0), buf.size, fp)
    }

    fclose(fp)
    return str.toString()
}

/**
 * Write text file
 *
 * POSIX implementation
 * https://www.nequalsonelifestyle.com/2020/11/16/kotlin-native-file-io/
 * https://stackoverflow.com/a/57124947
 */
@OptIn(ExperimentalForeignApi::class)
fun fsWriteFile(
    path: String,
    contents: String
) {
    val fp = fopen(path, "w")

    if (fp == null) {
        println("LHA-ERR fsWriteFile-1 path: '$path'")
    }

    var ok = fputs(contents, fp)
    if (ok == EOF) {
        println("LHA-ERR fsWriteFile-2 path: '$path'")
    }

    fclose(fp)
}
