package org.opengamestudio

import java.io.*

/**
 * List files of the provided directory
 *
 * JVM implementation
 */
fun fsListFiles(path: String): Array<FSFile> {
    var items = arrayOf<FSFile>()
    val list = File(path).listFiles()
    for (file in list) {
        val item =
            FSFile(
                file.isDirectory(),
                file.isFile(),
                file.getName(),
            )
        items += item
    }
    return items
}
