
fun main() {

    val inLines = readInput("input06_1")
    var input = inLines[0]

    var str = StringBuilder()
    str.append(input.substring(1, 4))
    var cp = 4

    while (cp < input.length) {
        cp++
        str.append(input[cp - 1])
        if (str.toSet().size == 4) {
            break
        }
        str = str.deleteAt(0)
    }

    println(cp)
}
