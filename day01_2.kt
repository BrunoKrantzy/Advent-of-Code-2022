

fun main() {

    val inLines = readInput("input01_1")
    var vMax = 0L
    var vM = 0L

    var lstV = mutableListOf<Long>()

    inLines.forEach {
        if (it.isBlank()) {
            lstV.add(vM)
            vM = 0L
        }
        else {
            vM += it.toLong()
        }
    }

    lstV.sortDescending()
    for (i in 0..2) {
        vMax += lstV[i]
    }

    println(vMax)

}


