package org.opengamestudio

/* Print debug information to console
 *
 * Conditions:
 * 1. A field has been changed
 */
fun pltDebug(p: Platform) {
    val key = p.c.recentField
    val value = "${p.c.fieldAny(p.c.recentField)}".take(30)
    println("LHA-DBG key/value: '$key'/'$value'")
}

/* Print to console
 *
 * Conditions:
 * 1. Application did launch
 */
fun pltPrintToConsole(p: Platform) {
    if (
        p.c.recentField == "consoleOutput"
    ) {
        println(p.c.consoleOutput)
    }
}
