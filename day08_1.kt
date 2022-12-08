
fun main() {

    var nbL = 99
    var nbC = 99
    var pL = 2
    var pR = 98
    var pH = 2
    var pB = 98

    var rep = (nbL * 4) - 4

    var tab2D: Array<Array<Int>> = Array (nbL+1) { Array(nbL+1) { -1 } }

    val inLines = readInput("input08_1")

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

    var oKgauche = true
    var oKdroite = true
    var oKhaut = true
    var oKbas = true

    for (ligne in pL..pR) { //ligne
        for (col in pH..pB) { // col.

            var v = tab2D[ligne][col]

            // gauche
            for (iC in 1 until col) {
                var vjC = tab2D[ligne][iC]
                if (vjC >= v) {
                    oKgauche = false
                    break
                }
            }
            // droite
            for (iC in col+1 .. nbC) {
                var vjC = tab2D[ligne][iC]
                if (vjC >= v) {
                    oKdroite = false
                    break
                }
            }

            // haut
            for (iL in 1 until ligne) {
                var viL = tab2D[iL][col]
                if (viL >= v) {
                    oKhaut = false
                    break
                }
            }
            // bas
            for (iL in ligne+1 .. nbL) {
                var viL = tab2D[iL][col]
                if (viL >= v) {
                    oKbas = false
                    break
                }
            }

            if (oKgauche || oKdroite || oKhaut || oKbas)
                rep++

            oKgauche = true
            oKdroite = true
            oKhaut = true
            oKbas = true
        }
    }

    println(rep)
}
