package org.opengamestudio

data class NetRequest(
    var body: String = "",
    var method: String = "",
    var path: String = "",
) {}
