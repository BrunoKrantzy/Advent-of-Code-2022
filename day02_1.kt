
fun calcPoints(str: String) : Int {
    var points = 0
    var c1 = str[0]
    var c2 = str[2]

    when (c1) {
        'A' -> {
            when (c2) {
                'X' -> points = 1 + 3
                'Y' -> points = 2 + 6
                'Z' -> points = 3
            }
        }

        'B' -> {
            when (c2) {
                'X' -> points = 1
                'Y' -> points = 2 + 3
                'Z' -> points = 3 + 6
            }
        }

        'C' -> {
            when (c2) {
                'X' -> points = 1 + 6
                'Y' -> points = 2
                'Z' -> points = 3 + 3
            }
        }
    }

    return points
}


fun main() {

    val inLines = readInput("input02_1")
    var rep = 0

    inLines.forEach {
        rep += calcPoints(it)
    }

    println(rep)
}

