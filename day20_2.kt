
import kotlin.math.abs

fun main() {

    var lstMapMoving = mutableListOf<Pair<Int,Long>>()
    var lstOriginale = mutableListOf<Long>()
    var key0 = Pair(0, 0L)

    fun calculeIter (vIter:Long, nNum:Int, posEl:Int) : Long {
        var posCible = 0
        var vIt = vIter

        var modIter = (vIt.mod(nNum))
        if (modIter == 0)
            return posEl.toLong()

        if (posEl + modIter > nNum - 1) {
            posCible = (modIter - (nNum - posEl))
            if (posCible == 0)
                posCible = nNum - 1
            return posCible.toLong()
        }
        else {
            return (modIter + posEl).toLong()
        }
    }


    fun deplace (el:Pair<Int, Long>, nM:Long, dir:String) {

        var posOrg = lstMapMoving.indexOf(el)
        var pos = 0
        var max = lstMapMoving.size - 1

        if (dir == "A") {
            pos = (posOrg + nM).toInt()

            if (pos > max) {
                pos -= max
            }

            lstMapMoving.removeAt(posOrg)
            lstMapMoving.add(pos, el)
        }
        else {
            pos = (posOrg - nM).toInt()

            if (pos < 0) {
                pos = max - abs(pos)
            }
            else if (pos == 0) {
                pos = max
            }

            lstMapMoving.removeAt(posOrg)
            lstMapMoving.add(pos, el)
        }
    }

    val inLines = readInput("input20_1")

    var decripKey = 811589153L

    inLines.forEachIndexed { idx, it ->
        lstOriginale.add(decripKey * it.toLong())
        lstMapMoving.add(Pair(idx, (decripKey * it.toLong())))

        if (it.toLong() == 0L) {
            val p = Pair(idx, it.toLong())
            key0 = p
        }
    }

    val nNum = inLines.size

    for (times in 1..10) {

        for (i in 0 until nNum) {
            val n = lstOriginale[i]
            val nAbs = abs(n)
            val element = Pair(i, n)
            var nbMove = 0L
            var direcMove = ""

            if (n == 0L || nAbs == (nNum - 1).toLong())
                continue
            else if (nAbs == nNum.toLong()) {
                nbMove = 1L
            }
            else if (nAbs > nNum) {
                nbMove = nAbs.mod(nNum - 1L)
                if (nbMove == 0L || nbMove == (nNum - 1).toLong())
                    continue
                else if (nbMove == nNum.toLong())
                    nbMove = 1L
            }
            else
                nbMove = nAbs

            if (n < 0)
                direcMove = "R"
            else
                direcMove = "A"

            deplace(element, nbMove, direcMove)
        }
    }

    val posElement = lstMapMoving.indexOf(key0)

    var v1000 = calculeIter(1000L, nNum, posElement)
    v1000 = lstMapMoving[v1000.toInt()].second

    var v2000 = calculeIter(2000L, nNum, posElement)
    v2000 = lstMapMoving[v2000.toInt()].second

    var v3000 = calculeIter(3000L, nNum, posElement)
    v3000 = lstMapMoving[v3000.toInt()].second

    var rep = v1000 + v2000 + v3000
    println(rep)
}

