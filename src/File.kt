package org.opengamestudio

data class File(
    var isDirectory: Boolean = false,
    var isFile: Boolean = false,
    var name: String = "",
) {}
