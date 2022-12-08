
fun main() {

    var nbL = 99
    var nbC = 99
    var pL = 2
    var pR = 98
    var pH = 2
    var pB = 98

    val inLines = readInput("input08_1")

    var tab2D: Array<Array<Int>> = Array (nbL+1) { Array(nbL+1) { -1 } }
    var cl = 1
    var cc = 1
    inLines.forEach {
        it.forEach { it2 ->
            tab2D[cl][cc] = it2.code - 48
            cc++
        }
        cl++
        cc = 1
    }

    var arbG = 0
    var arbD = 0
    var arbH = 0
    var arbB = 0
    var bestLook = 0

    for (ligne in pL..pR) { //ligne
        for (col in pH..pB) { // col.
            var v = tab2D[ligne][col]

            // gauche
            for (iC in col-1 downTo 1) {
                arbG++
                var vjC = tab2D[ligne][iC]
                if (vjC >= v) {
                    break
                }
            }
            // droite
            for (iC in col+1 .. nbC) {
                arbD++
                var vjC = tab2D[ligne][iC]
                if (vjC >= v) {
                    break
                }
            }

            // haut
            for (iL in ligne-1 downTo 1) {
                arbH++
                var viL = tab2D[iL][col]
                if (viL >= v) {
                    break
                }
            }
            // bas
            for (iL in ligne+1 .. nbL) {
                arbB++
                var viL = tab2D[iL][col]
                if (viL >= v) {
                    break
                }
            }

            var look = arbG * arbD * arbH * arbB
            bestLook = maxOf(bestLook, look)

            arbG = 0
            arbD = 0
            arbH = 0
            arbB = 0
        }
    }

    println(bestLook)
}

