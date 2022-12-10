
import java.lang.Math.abs

fun main() {

    //val inLines = readInput("test")
    val inLines = readInput("input09_1")

    var rep = 0

    data class posNode (var posL: Int, var posC: Int)

    var nbLignes = 10000
    var nbCols = 10000
    var tabJeu = Array (nbLignes) { Array(nbCols) { false } }

    var posHead = posNode(0, 0)
    var posTail = posNode(0, 0)

    fun calcPosTail(mv:String, dep:Int, pHead: posNode, pTail: posNode) : posNode {
        var pTail = pTail
        var pHead = pHead
        var Ldif = abs(pHead.posL - pTail.posL)
        var Cdif = abs(pHead.posC - pTail.posC)

        if (Ldif < 2 && Cdif < 2) {
            pTail = posTail
            return pTail
        }

        if (Ldif == 0) { // sur la même ligne
            if (mv == "R") { // changement de colonne
                for (i in pTail.posC until pTail.posC + Cdif) {
                    tabJeu[pHead.posL][i] = true
                }
                pTail.posC += Cdif - 1
                pTail.posL = pHead.posL
            }
            else if (mv == "L") {
                for (i in pTail.posC downTo pTail.posC - (Cdif - 1)) {
                    tabJeu[pHead.posL][i] = true
                }
                pTail.posC -= Cdif - 1
                pTail.posL = pHead.posL
            }
            else {
                println("ERREUR 001")
            }
        }
        else if (Cdif == 0) { // dans la même colonne
            if (mv == "D") { // changement de ligne
                for (i in pTail.posL until pTail.posL + Ldif) {
                    tabJeu[i][pHead.posC] = true
                }
                pTail.posL += Ldif - 1
                pTail.posC = pHead.posC
            }
            else if (mv == "U") {
                for (i in pTail.posL downTo  pTail.posL - (Ldif - 1)) {
                    tabJeu[i][pHead.posC] = true
                }
                pTail.posL -= Ldif - 1
                pTail.posC = pHead.posC
            }
            else {
                println("ERREUR 002")
            }
        }
        else { // diagonale
            if (Cdif == 1) { // Dep. U ou D de Head
                if (mv == "D") { // changement de ligne
                    for (i in pTail.posL + 1 until pTail.posL + Ldif) {
                        tabJeu[i][pHead.posC] = true
                    }
                    pTail.posL += Ldif - 1
                    pTail.posC = pHead.posC
                }
                else if (mv == "U") {
                    for (i in pTail.posL - 1 downTo  pTail.posL - (Ldif - 1)) {
                        tabJeu[i][pHead.posC] = true
                    }
                    pTail.posL -= Ldif - 1
                    pTail.posC = pHead.posC
                }
                else {
                    println("ERREUR 003")
                }
            }
            else if (Ldif == 1) { // Dep. L ou R de Head
                if (mv == "R") { // changement de colonne
                    for (i in pTail.posC + 1 until pTail.posC + Cdif) {
                        tabJeu[pHead.posL][i] = true
                    }
                    pTail.posC += Cdif - 1
                    pTail.posL = pHead.posL
                }
                else if (mv == "L") {
                    for (i in pTail.posC - 1 downTo pTail.posC - (Cdif - 1)) {
                        tabJeu[pHead.posL][i] = true
                    }
                    pTail.posC -= Cdif - 1
                    pTail.posL = pHead.posL
                }
                else {
                    println("ERREUR 004")
                }
            }
            else {
                println("ERREUR 005")
            }
        }

        return pTail
    }

    // positions de départ
    var lH = 5000
    var cH = 5000
    tabJeu[lH][cH] = true
    posHead = posNode(lH, cH)
    posTail = posNode(lH, cH)

    inLines.forEach {

        var regex = "^(R|L|U|D) (\\d+)".toRegex()
        var match = regex.find(it)!!
        val move = match.groups[1]!!.value.toString()
        val dist = match.groups[2]!!.value.toInt()

        when (move) {
            "U" -> {
                posHead.posL -= dist
                posTail = calcPosTail("U", dist, posHead, posTail)
            }
            "D" -> {
                posHead.posL += dist
                posTail = calcPosTail("D", dist, posHead, posTail)
            }
            "R" -> {
                posHead.posC += dist
                posTail = calcPosTail("R", dist, posHead, posTail)
            }
            "L" -> {
                posHead.posC -= dist
                posTail = calcPosTail("L", dist, posHead, posTail)
            }
        }
    }

    tabJeu.forEach {
        it.forEach { it2 ->
            if (it2) rep++
        }
    }

    println(rep)
}

