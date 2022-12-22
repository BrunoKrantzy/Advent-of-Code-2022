import kotlin.math.abs

fun main() {

    var lstMapMoving = mutableListOf<Pair<Int,Int>>()
    var lstOriginale = mutableListOf<Int>()
    var key0 = Pair(0, 0)


    fun calculeIter (vIter:Int, nNum:Int, posEl:Int) : Int {
        var posCible = 0
        var vIt = vIter

        var modIter = vIt % nNum
        if (modIter == 0)
            return posEl

        if (posEl + modIter > nNum - 1) {
            posCible = modIter - (nNum - posEl)
            if (posCible == 0)
                posCible = nNum - 1
            return posCible
        }
        else {
            return modIter + posEl
        }
    }


    fun deplace (el:Pair<Int, Int>, nM:Int, dir:String) {

        var posOrg = lstMapMoving.indexOf(el)
        var pos = 0
        var max = lstMapMoving.size - 1

        if (dir == "A") {
            pos = posOrg + nM

            if (pos == max + 1)
                return

            if (pos > max) {
                pos -= max
            }

            lstMapMoving.removeAt(posOrg)
            lstMapMoving.add(pos, el)
        }
        else {
            pos = posOrg - nM

            if (pos < 0) {
                pos = (lstMapMoving.size-1) - abs(pos)
            }
            else if (pos == 0) {
                pos = (lstMapMoving.size-1)
            }

            lstMapMoving.removeAt(posOrg)
            lstMapMoving.add(pos, el)
        }
    }

    val inLines = readInput("input20_1")

    inLines.forEachIndexed { idx, it ->
        lstOriginale.add(it.toInt())
        lstMapMoving.add(Pair(idx, it.toInt()))

        if (it.toInt() == 0) {
            var p = Pair(idx, it.toInt())
            key0 = p
        }
    }

    val nNum = inLines.size

    for (i in 0 until nNum) {
        val n = lstOriginale[i]
        val nAbs = abs(n)
        val element = Pair(i, n)
        var nbMove = 0
        var direcMove = ""

        if (n == 0 || nAbs == (nNum - 1))
            continue
        else if (nAbs == nNum) {
            nbMove = 1
        }
        else if (nAbs >= nNum) {
            nbMove = nAbs % (nNum - 1)
        }
        else {
            nbMove = nAbs
        }

        if (n < 0)
            direcMove = "R"
        else
            direcMove = "A"

        deplace(element, nbMove, direcMove)
    }

    val posElement = lstMapMoving.indexOf(key0)

    var v1000 = calculeIter(1000, nNum, posElement)
    v1000 = lstMapMoving[v1000].second

    var v2000 = calculeIter(2000, nNum, posElement)
    v2000 = lstMapMoving[v2000].second

    var v3000 = calculeIter(3000, nNum, posElement)
    v3000 = lstMapMoving[v3000].second

    var rep = v1000 + v2000 + v3000
    println(rep)
}

