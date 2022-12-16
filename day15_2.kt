import java.lang.Math.abs

//data class ContenuLigne(val ID:Int, val rangeCol:IntRange, val posBeacon:Pair<Int, Int>)

fun main() {

    var lstDatas = mutableListOf<ContenuLigne>()

    //val inLines = readInput("test")
    //var maxCoor = 20

    val inLines = readInput("input15_1")
    var maxCoor = 400

    var multiPara = 4000000L

    fun printResult(sP:MutableSet<Pair<Int, Int>>) {
        var beacon = sP.first()
        var result:Long = (beacon.second * multiPara) + beacon.first
        println(result)
        return
    }

    inLines.forEach {
        var regex = "^Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)".toRegex()
        var match = regex.find(it)
        if (match != null) {
            val sCol = match.groups[1]!!.value.toInt()
            val sLig = match.groups[2]!!.value.toInt()
            val bCol = match.groups[3]!!.value.toInt()
            val bLig = match.groups[4]!!.value.toInt()

            val distMan = abs(sLig - bLig) + abs(sCol - bCol)
            val pBeacon = Pair(bCol, bLig)

            var cp = 0
            for (i in sLig - 1 downTo sLig - distMan) {
                cp += 2
                var nbCols = ((distMan * 2) - cp) / 2
                val vR = IntRange(sCol-nbCols, sCol+nbCols)
                val idLig = i
                var datas = ContenuLigne(idLig, vR, pBeacon)
                lstDatas.add(datas)
            }

            // ligne du Sensor
            val vRs = IntRange(sCol-distMan, sCol+distMan)
            var datas = ContenuLigne(sLig, vRs, pBeacon)
            lstDatas.add(datas)

            cp = 0
            for (i in sLig + 1 .. sLig + distMan) {
                cp += 2
                var nbCols = ((distMan * 2) - cp) / 2
                val vR = IntRange(sCol-nbCols, sCol+nbCols)
                val idLig = i
                var data = ContenuLigne(idLig, vR, pBeacon)
                lstDatas.add(data)
            }
        }
    }

    var setPos = mutableSetOf<Pair<Int, Int>>()
    var tabCoor = IntArray(maxCoor+1) { 0 }

    // after first input
    val startTime = System.currentTimeMillis()

    for (i in 0 .. maxCoor) {
        var lstLignesID = lstDatas.filter { it.ID == i }
        if (lstLignesID.size > 0) {

            var tabCoor = IntArray(maxCoor+1) { 0 }

            lstLignesID.forEach { it2 ->
                val range = it2.rangeCol
                range.forEach { itR ->
                    if (itR > -1 && itR <= maxCoor)
                    tabCoor[itR] = 1
                }
            }

            if (tabCoor.contains(0)) {
                var j = tabCoor.indexOf(0)
                setPos.add(Pair(i, j))
                printResult(setPos)
                return
            }
        }

        if (i % 100 == 0) {
            println("Ligne $i")
            val endTime = System.currentTimeMillis()
            val processTime = endTime - startTime
            println("Completed in $processTime ms")
        }
    }
}


