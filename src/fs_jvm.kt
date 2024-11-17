package org.opengamestudio

import java.io.*

/**
 * List files of the provided directory
 *
 * JVM implementation
 */
fun fsListFiles(dir: String): Array<FSFile> {
    var items = arrayOf<FSFile>()
    val list = File(dir).listFiles()
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
