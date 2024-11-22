package org.opengamestudio

import kotlinx.cinterop.*
import platform.posix.*

/*
val DT_DIR: UByte = 4
val DT_LNK: UByte = 10
val DT_REG: UByte = 8
*/

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
                println("fsLF name/type: '${entry.pointed.d_name.toKString()}'/'${entry.pointed.d_type}'")
                if (entry.pointed.d_type == DT_LNK.toUByte()) {
                    println("link")
                }
                var ret: Int = 0
                memScoped {
                    var st = alloc<stat>()

                    ret = stat(entry.pointed.d_name.toKString(), st.ptr)
                    println("fsLF stat int/isDir: '${st.st_mode}'/'${(st.st_mode.toInt() and S_IFMT) == S_IFDIR}'")
                }
                entry = readdir(dir)
            }
        } finally {
            closedir(dir)
        }
    }
    return items
}
