
fun main() {

    val inLines = readInput("input06_1")
    var input = inLines[0]

    var str = StringBuilder()
    str.append(input.substring(1, 14))
    var cp = 14

    while (cp < input.length) {
        cp++
        str.append(input[cp - 1])
        if (str.toSet().size == 14) {
            break
        }
        str = str.deleteAt(0)
    }

    println(cp)
}
