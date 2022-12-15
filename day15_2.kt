import java.lang.Math.abs

//data class ContenuLigne(val ID:Int, val rangeCol:IntRange, val posBeacon:Pair<Int, Int>)

fun main() {

    var lstDatas = mutableListOf<ContenuLigne>()

    //val inLines = readInput("test")
    //var maxCoor = 20

    val inLines = readInput("input15_1")
    var maxCoor = 4000000

    var multiPara = 4000000L

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
    lstDatas.forEach {
        if (it.ID > -1 && it.ID <= maxCoor) {
            val range = it.rangeCol
            for (elem in range) {
                if (elem > -1 && elem <= maxCoor)
                    setPos.add(Pair(it.ID, elem))
            }
        }
    }

    lstDatas.clear()

    var beacon: Pair<Int, Int>
    beacon = Pair(-1, -1)
    for (i in 0..maxCoor) {
        for (j in 0 .. maxCoor) {
            val p = Pair(i, j)
            if (!setPos.contains(p)) {
                println("ligne ${p.first} - col. ${p.second}")
                beacon = Pair(p.first, p.second)
            }
        }
    }

    var result:Long = (beacon.second * multiPara) + beacon.first
    println(result)
}

