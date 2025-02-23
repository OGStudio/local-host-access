package org.opengamestudio

// File system item representation
data class FSFile(
    var isDirectory: Boolean = false,
    var isFile: Boolean = false,
    var name: String = "",
) {}

// Client request representation
data class NetRequest(
    var body: String = "",
    var method: String = "",
    var path: String = "",
) {}
