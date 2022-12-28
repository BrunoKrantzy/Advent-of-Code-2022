
fun main() {

    data class Dependances(var name1:String, var v1:Long, var name2:String, var v2:Long, var op:String)

    var mapNameVal = mutableMapOf<String, Long>()
    var mapDependants = mutableMapOf<String, MutableList<String>>()
    var mapDependance = mutableMapOf<String, Dependances>()

    val inLines = readInput("input21_1")

    inLines.forEach {

        var regex = "^([a-z]+): ([a-z]+) (\\+|-|\\*|\\/) ([a-z]+)".toRegex()
        var match = regex.find(it)
        if (match != null) {
            val name = match.groups[1]!!.value
            val n1 = match.groups[2]!!.value
            val op = match.groups[3]!!.value
            val n2 = match.groups[4]!!.value

            mapNameVal[name] = -1

            val dep = Dependances(n1, -1, n2, -1, op)
            mapDependance[name] = dep

            if (mapDependants.containsKey(n1)) {
                mapDependants[n1]?.add(name)
            } else {
                var lstDep = mutableListOf<String>()
                lstDep.add(name)
                mapDependants[n1] = lstDep
            }

            if (mapDependants.containsKey(n2)) {
                mapDependants[n2]?.add(name)
            } else {
                var lstDep = mutableListOf<String>()
                lstDep.add(name)
                mapDependants[n2] = lstDep
            }
        }

        regex = "^([a-z]+): (\\d+)".toRegex()
        match = regex.find(it)
        if (match != null) {
            val name = match.groups[1]!!.value
            val v = match.groups[2]!!.value.toLong()

            mapNameVal[name] = v
        }
    }

    var rootInconnu = true
    while (rootInconnu) {
        var lstConnus = mapNameVal.filter { it.value != -1L }
        lstConnus.forEach {
            val nom = it.key
            if (mapDependants.containsKey(nom)) {
                val lstDep = mapDependants[nom]
                if (lstDep != null) {
                    lstDep.forEach { it2 ->
                        if (mapDependance[it2]?.name1 == nom) {
                            mapDependance[it2]?.v1 = it.value
                        }
                        else if (mapDependance[it2]?.name2 == nom) {
                            mapDependance[it2]?.v2 = it.value
                        }
                    }
                }
            }
        }

        mapDependance.forEach {
            val nom = it.key
            if (it.value.v1 != -1L && it.value.v2 != -1L) {
                val v1 = it.value.v1
                val v2 = it.value.v2
                val oper = it.value.op
                var result = -1L
                when (oper) {
                    "+" -> result = v1 + v2
                    "-" -> result = v1 - v2
                    "*" -> result = v1 * v2
                    "/" -> result = v1 / v2
                }
                mapNameVal[nom] = result
            }
        }

        if (mapNameVal["root"] != -1L) {
            rootInconnu = false
        }
    }

    println("${mapNameVal["root"]}")
}

