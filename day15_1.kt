import java.lang.Math.abs

//data class ContenuLigne(val ID:Int, val rangeCol:IntRange, val posBeacon:Pair<Int, Int>)

fun main() {

    val lstDatas = mutableListOf<ContenuLigne>()

    val inLines = readInput("input15_1")
    val ligneRes = 2000000

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
                val nbCols = ((distMan * 2) - cp) / 2
                val vR = IntRange(sCol-nbCols, sCol+nbCols)
                val idLig = i
                val datas = ContenuLigne(idLig, vR, pBeacon)
                lstDatas.add(datas)
            }

            // ligne du Sensor
            val vRs = IntRange(sCol-distMan, sCol+distMan)
            val data = ContenuLigne(sLig, vRs, pBeacon)
            lstDatas.add(data)

            cp = 0
            for (i in sLig + 1 .. sLig + distMan) {
                cp += 2
                val nbCols = ((distMan * 2) - cp) / 2
                val vR = IntRange(sCol-nbCols, sCol+nbCols)
                val idLig = i
                val datas = ContenuLigne(idLig, vR, pBeacon)
                lstDatas.add(datas)
            }
        }
    }

    var colMin = Int.MAX_VALUE
    var colMax = -Int.MAX_VALUE
    val lstLignesRes = lstDatas.filter { it.ID == ligneRes }
    lstLignesRes.forEach {
        val vMin = it.rangeCol.first
        colMin = minOf(colMin, vMin)
        val vMax = it.rangeCol.last
        colMax = maxOf(colMax, vMax)
    }

    val setBeacons = mutableSetOf<Int>()
    val beaconOnLig = lstDatas.filter { it.posBeacon.second == ligneRes }
    beaconOnLig.forEach {
        setBeacons.add(it.posBeacon.first)
    }
    val nbBeaconOnLig = setBeacons.size

    println(1 + (colMax - colMin) - nbBeaconOnLig)
}

