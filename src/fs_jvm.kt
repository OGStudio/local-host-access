package org.opengamestudio

import java.io.*
//import kotlin.io.*

/**
 * List files of the provided directory
 *
 * JVM implementation
 */
fun fsListFiles(dir: String)/*: Array<String>*/ {
    val list = File(dir).listFiles()
    for (file in list) {
        println("fsLF: '${file.name}'")
    }
}
