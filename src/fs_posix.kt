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
