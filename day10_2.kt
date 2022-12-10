
fun main() {

    val inLines = readInput("input10_1")

    var tabRep = Array (6) { Array(40) { "." } }
    var nCycle = 0
    var regX = 1
    var nextCalcul = 41
    var ligTab = 0

    var lstParams = mutableListOf<Int>()
    var lstCyX = mutableListOf<Int>()

    fun majRegX() {
        var v = 0
        if (lstCyX.isNotEmpty()) {
            if (nCycle == lstCyX.first()) {
                v = lstParams.first()
                regX += v
                lstCyX.removeFirst()
                lstParams.removeFirst()
            }
        }
    }

    fun testCycle() {
        if (nCycle == nextCalcul) {
            nextCalcul += 40
            ligTab++
        }

        val pSprite = IntRange(regX, regX+2)
        val posLigne = (nCycle - 1) - (ligTab * 40)
        if (pSprite.contains(posLigne)) {
            tabRep[ligTab][posLigne-1] = "#"
        }
    }

    inLines.forEach {
        var param = 0

        var regex = "^noop".toRegex()
        var match = regex.find(it)
        if (match != null) {
            nCycle++
            majRegX()
            testCycle()
        }

        regex = "^addx (-?\\d+)".toRegex()
        match = regex.find(it)
        if (match != null) {

            nCycle++
            majRegX()
            testCycle()

            nCycle++
            majRegX()
            testCycle()

            param = match.groups[1]!!.value.toInt()

            if (param != 0) {
                lstParams.add(param)
                lstCyX.add(nCycle + 2)
            }
        }
    }

    tabRep.forEach {
        it.forEach {it2 ->
            print(it2)
        }
        print("\n")
    }
}
