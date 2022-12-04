

fun main() {

    val inLines = readInput("input04_1")
    var rep = 0

    inLines.forEach {

        val regex = "^(\\d+)-(\\d+),(\\d+)-(\\d+)".toRegex()
        val match = regex.find(it)!!
        val eAd = match.groups[1]!!.value.toInt()
        val eAf = match.groups[2]!!.value.toInt()
        val eBd = match.groups[3]!!.value.toInt()
        val eBf = match.groups[4]!!.value.toInt()

        val r1 = IntRange(eAd, eAf)
        val r2 = IntRange(eBd, eBf)

        if ((r1.contains(r2.first) && r1.contains(r2.last)) || (r2.contains(r1.first) && r2.contains(r1.last)))
            rep++
    }

    println(rep)
}
