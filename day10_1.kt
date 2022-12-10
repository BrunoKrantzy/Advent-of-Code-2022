
fun main() {

    val inLines = readInput("input10_1")

    var rep = 0
    var nCycle = 0
    var regX = 1
    var nextCalcul = 21

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
            var c = (nCycle - 1) * regX
            rep += c
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

    println(rep)
}
