

fun main() {

    val inLines = readInput("input03_1")
    var rep = 0

    var cp = -1
    var nbLines = inLines.size

    while (cp < nbLines - 1) {
        cp++
        val s1 = inLines[cp]
        cp++
        val s2 = inLines[cp]
        cp++
        val s3 = inLines[cp]

        for (element in s1) {
            if (s2.contains(element) && s3.contains(element)) {
                val vCode = element.code
                rep += if (vCode > 96)
                    (vCode - 96)
                else
                    (vCode - 38)
                break
            }
        }
    }

    println(rep)
}
