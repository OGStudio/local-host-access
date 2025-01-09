package org.opengamestudio
data class FSFile(
    var isDirectory: Boolean = false,
    var isFile: Boolean = false,
    var name: String = "",
) {}
data class NetRequest(
    var body: String = "",
    var method: String = "",
    var path: String = "",
) {}
