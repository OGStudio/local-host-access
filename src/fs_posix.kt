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
fun fsListFiles(path: String)/*: Array<String>*/ {
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
