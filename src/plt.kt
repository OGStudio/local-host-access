package org.opengamestudio

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
