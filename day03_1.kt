

fun main() {

    val inLines = readInput("input03_1")
    var rep = 0

    inLines.forEach {

        val len = it.length / 2
        val s1 = it.substring(0, len)
        val s2 = it.substring(len)

        for (element in s1) {
            if (s2.contains(element)) {
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
