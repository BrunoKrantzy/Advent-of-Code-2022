
fun main() {

    var path = MutableStack<Int>()
    path.push(0)
    var idParent = path.peek()

    var dirList = mutableListOf<Directory>()
    dirList.add(Directory(0, idParent, 0L))

    val inLines = readInput("input07_1")

    var dirCible = 0

    for (it in inLines) {

        var regex = "^\\$ cd ([a-z]+)".toRegex()
        var match = regex.find(it)
        if (match != null) {
            dirCible++
            idParent = path.peek()
            path.push(dirCible)
            dirList.add(Directory(dirCible, idParent, 0L))
        }

        regex = "^\\$ cd (\\.\\.)".toRegex()
        match = regex.find(it)
        if (match != null) {
            path.pop()
        }

        regex = "^(\\d+)".toRegex()
        match = regex.find(it)
        if (match != null) {
            for (i in dirList) {
                var idx = 0
                if (i.parent == idParent && i.nomDir == path.peek()) {
                    idx = dirList.indexOf(i)
                    dirList[idx].tailleDir += match.groups[1]?.value?.toLong()!!
                    break
                }
            }
        }
    }

    for (it in dirList.size-1 downTo 1) {
        if (dirList[it].parent != 0) {
            for (i in 1 until dirList.size) {
                if (dirList[i].nomDir == dirList[it].parent) {
                    dirList[i].tailleDir += dirList[it].tailleDir
                }
            }
        }
    }

    var sizeAllDir = 0L
    dirList.forEach {
        var sz = it.tailleDir
        if (sz <= 100000)
            sizeAllDir += sz
    }

    println(sizeAllDir)
}
