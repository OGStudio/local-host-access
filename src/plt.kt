package org.opengamestudio

/* Print to console
 *
 * Conditions:
 * 1. Application did launch
 */
fun pltPrintToConsole(
    c: Context
) {
    if (
        c.recentField == "didLaunch"
    ) {
        println("Local Host Access, version ?")
    }
}

