
data class datasMonkey(var ope:String, var vOpe:String, var vTest: Int, var cibleT:Int, var cibleF:Int)

fun main() {

    val inLines = readInput("input11_1")

    val tabMonkey = Array(20) { mutableListOf<Long>() }
    val lstMonkey = mutableListOf<datasMonkey>()
    val tabCompteurs = Array(20) { 0L }
    var noMonkey = 0
    var datasTemp = datasMonkey("", "", 0, 0, 0)

    for (it in inLines) {

        if (it.isBlank())
            continue

        var regex = "^Monkey (\\d+)".toRegex()
        var match = regex.find(it)
        if (match != null) {
            var noMonk = match.groups[1]!!.value.toInt()
            noMonkey = noMonk
        }

        regex = "^\\s+Starting items: ([\\d+,?\\s{1}]+)".toRegex()
        match = regex.find(it)
        if (match != null) {
            var strItems = match.groups[1]!!.value
            var lstItems = strItems.split(", ")
            lstItems.forEach {
                tabMonkey[noMonkey].add(it.toLong())
            }
        }

        regex = "^\\s+Operation: new = old (\\*|\\+) (\\d+|old)".toRegex()
        match = regex.find(it)
        if (match != null) {
            var tOpe = match.groups[1]!!.value
            var vOpe = match.groups[2]!!.value
            datasTemp.ope = tOpe
            datasTemp.vOpe = vOpe
        }

        regex = "^\\s+Test: divisible by (\\d+)".toRegex()
        match = regex.find(it)
        if (match != null) {
            var vDiv = match.groups[1]!!.value.toInt()
            datasTemp.vTest = vDiv
        }

        regex = "^\\s+If true: throw to monkey (\\d+)".toRegex()
        match = regex.find(it)
        if (match != null) {
            var mCibleT = match.groups[1]!!.value.toInt()
            datasTemp.cibleT = mCibleT
        }

        regex = "^\\s+If false: throw to monkey (\\d+)".toRegex()
        match = regex.find(it)
        if (match != null) {
            var mCibleF = match.groups[1]!!.value.toInt()
            datasTemp.cibleF = mCibleF

            lstMonkey.add(datasTemp.copy())
        }
    }

    var MOD = 1L
    lstMonkey.forEach {
        MOD = MOD * it.vTest
    }

    var nbRounds = 10000
    for (r in 1 .. nbRounds) {
        for (i in 0 until lstMonkey.size) {
            var datas = lstMonkey[i]

            val tO = datas.ope
            val vO = datas.vOpe
            val vT = datas.vTest
            val cT = datas.cibleT
            val cF = datas.cibleF

            var lstTemp = mutableListOf<Long>()
            lstTemp.addAll(tabMonkey[i])

            for (it in lstTemp) {

                tabCompteurs[i]++

                var wLevel = it
                var paramOp = 0L

                if (vO == "old")
                    paramOp = wLevel
                else
                    paramOp = vO.toLong()

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

                wLevel = wLevel % MOD

                if (wLevel % vT == 0L) { // cible T
                    tabMonkey[cT].add(wLevel)
                } else { // cible F
                    tabMonkey[cF].add(wLevel)
                }

                tabMonkey[i].removeFirst()
            }
        }
    }

    tabCompteurs.sortDescending()
    var rep = tabCompteurs[0] * tabCompteurs[1]

    println(rep)
}
