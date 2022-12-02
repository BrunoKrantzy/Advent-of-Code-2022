
fun calcPoints2(str: String) : Int {
    var points = 0
    var c1 = str[0]
    var c2 = str[2]

    when (c1) {
        'A' -> {
            when (c2) {
                'X' -> points = 0 + 3
                'Y' -> points = 3 + 1
                'Z' -> points = 6 + 2
            }
        }

        'B' -> {
            when (c2) {
                'X' -> points = 0 + 1
                'Y' -> points = 3 + 2
                'Z' -> points = 6 + 3
            }
        }

        'C' -> {
            when (c2) {
                'X' -> points = 0 + 2
                'Y' -> points = 3 + 3
                'Z' -> points = 6 + 1
            }
        }
    }

    return points
}


fun main() {

    val inLines = readInput("input02_1")
    var rep = 0

    inLines.forEach {
        rep += calcPoints2(it)
    }

    println(rep)
}

