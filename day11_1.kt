import kotlin.math.floor

data class datasMonkey(var ope:String, var vOpe:String, var vTest: Int, var cibleT:Int, var cibleF:Int)

fun main() {

    val inLines = readInput("input11_1")

    val tabMonkey = Array(20) { mutableListOf<Int>() }
    val lstMonkey = mutableListOf<datasMonkey>()
    val tabCompteurs = Array(20) { 0 }
    var noMonkey = 0
    val datasTemp = datasMonkey("", "", 0, 0, 0)

    for (it in inLines) {

        if (it.isBlank())
            continue

        var regex = "^Monkey (\\d+)".toRegex()
        var match = regex.find(it)
        if (match != null) {
            val noMonk = match.groups[1]!!.value.toInt()
            noMonkey = noMonk
        }

        regex = "^\\s+Starting items: ([\\d+,?\\s{1}]+)".toRegex()
        match = regex.find(it)
        if (match != null) {
            val strItems = match.groups[1]!!.value
            val lstItems = strItems.split(", ")
            lstItems.forEach {
                tabMonkey[noMonkey].add(it.toInt())
            }
        }

        regex = "^\\s+Operation: new = old (\\*|\\+) (\\d+|old)".toRegex()
        match = regex.find(it)
        if (match != null) {
            val tOpe = match.groups[1]!!.value
            val vOpe = match.groups[2]!!.value
            datasTemp.ope = tOpe
            datasTemp.vOpe = vOpe
        }

        regex = "^\\s+Test: divisible by (\\d+)".toRegex()
        match = regex.find(it)
        if (match != null) {
            val vDiv = match.groups[1]!!.value.toInt()
            datasTemp.vTest = vDiv
        }

        regex = "^\\s+If true: throw to monkey (\\d+)".toRegex()
        match = regex.find(it)
        if (match != null) {
            val mCibleT = match.groups[1]!!.value.toInt()
            datasTemp.cibleT = mCibleT
        }

        regex = "^\\s+If false: throw to monkey (\\d+)".toRegex()
        match = regex.find(it)
        if (match != null) {
            val mCibleF = match.groups[1]!!.value.toInt()
            datasTemp.cibleF = mCibleF

            lstMonkey.add(datasTemp.copy())
        }
    }

    var nbRounds = 20
    for (r in 1 .. nbRounds) {
        for (i in 0 until lstMonkey.size) {
            val datas = lstMonkey[i]
            val tO = datas.ope
            val vO = datas.vOpe
            val vT = datas.vTest
            val cT = datas.cibleT
            val cF = datas.cibleF

            val lstTemp = mutableListOf<Int>()
            lstTemp.addAll(tabMonkey[i])

            for (it in lstTemp) {
                tabCompteurs[i]++
                var wLevel = it
                var paramOp = 0

                if (vO == "old")
                    paramOp = wLevel
                else
                    paramOp = vO.toInt()

                when (tO) {
                    "*" -> {
                        wLevel *= paramOp
                    }
                    "+" -> {
                        wLevel += paramOp
                    }
                    else -> {
                        println("ERREUR OPE")
                        return
                    }
                }

                wLevel = floor(wLevel / 3.0).toInt()

                if (wLevel % vT == 0) { // cible T
                    tabMonkey[cT].add(wLevel)
                }
                else { // cible F
                    tabMonkey[cF].add(wLevel)
                }

                tabMonkey[i].removeFirst()
            }
        }
    }

    tabCompteurs.sortDescending()
    val rep = tabCompteurs[0] * tabCompteurs[1]

    println(rep)
}
