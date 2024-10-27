package org.opengamestudio

/* Print to console
 *
 * Conditions:
 * 1. Application did launch
 */
fun pltPrintToConsole(
    p: Platform
) {
    if (
        p.c.recentField == "didLaunch"
    ) {
        println("Local Host Access, version ?")
    }
}

