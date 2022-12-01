
fun main() {

    val inLines = readInput("input01_1")
    var vMax = 0L
    var vM = 0L

    inLines.forEach {
        if (it.isBlank()) {
            vMax = maxOf(vMax, vM)
            vM = 0L
        }
        else {
            vM += it.toLong()
        }
    }

    println(vMax)

}


