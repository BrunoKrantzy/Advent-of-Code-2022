
fun main() {

    val sOut = StringBuilder()

    var lstStacks = mutableListOf<MutableStack<Char>>()
    for (i in 1..10) {
        val stack0 = MutableStack<Char>()
        lstStacks.add(stack0) // index 0 à neutraliser
    }

    val inLines = readInput("input05_1")

    // établir la liste des infos de piles
    var lstCodes = mutableListOf<String>()
    for (element in inLines) {
        if (element[1] == '1') { // ligne de fin d'infos
            break
        }
        else {
            lstCodes.add(element)
        }
    }
    lstCodes.reverse() // pour empiler dans le bon ordre

    // construction de la liste des 9 piles
    var nL = 0
    for (element in lstCodes) {
        nL++
        var l = 1
        for (n in 1..9) {
            val c = element.substring(l, l + 1)
            if (c != " ") {
                lstStacks[n].push(c[0])
            }
            l += 4 // offset entre deux caractères utiles
        }
    }
    nL += 2 // sauter les 2 lignes précédant les commandes

    // gestion des commandes de déplacement des caisses
    for (line in nL until inLines.size) {
        val it = inLines[line]
        val regex = "^move (\\d+) from (\\d+) to (\\d+)".toRegex()
        val match = regex.find(it)!!
        val nbC = match.groups[1]!!.value.toInt()
        val sDep = match.groups[2]!!.value.toInt()
        val sFin = match.groups[3]!!.value.toInt()

        val stD = lstStacks[sDep]
        val stF = lstStacks[sFin]

        // utilisation d'une pile temporaire pour le déplacement de caisses en série
        val stackTemp = MutableStack<Char>()
        for (i in 1..nbC) {
            val c = stD.pop()
            stackTemp.push(c)
        }
        for (j in 1..nbC) {
            val c = stackTemp.pop()
            stF.push(c)
        }
    }

    // sortie de la solution
    for (i in 1 until lstStacks.size) {
        if (!lstStacks[i].isEmpty()) {
            val c = lstStacks[i].peek()
            sOut.append(c)
        }
    }

    println(sOut)
}

