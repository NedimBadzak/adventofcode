import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


fun main() {

    val reader = BufferedReader(InputStreamReader(File("input.txt").inputStream()))


    val start = System.currentTimeMillis()
    var line = reader.readLine()

    val list: MutableList<String> = mutableListOf()
    while (line != null) {
        list.add(line)
        line = reader.readLine()
    }

    val zbirovi = mutableListOf<Long>()
    var zbir = 0
    for (i in list.indices) {
        val otvaralice: MutableList<Char> = mutableListOf()
        var izbacio = false
        for (j in list[i].indices) {
            if (list[i][j] == '(' || list[i][j] == '{' || list[i][j] == '[' || list[i][j] == '<') {
                otvaralice.add(list[i][j])
            } else if (list[i][j] == ')') {
                if (otvaralice.last() == '(') otvaralice.removeAt(otvaralice.lastIndex)
                else {
                    zbir += 3
                    izbacio = true
                    break
                }
            } else if (list[i][j] == '}') {
                if (otvaralice.last() == '{') otvaralice.removeAt(otvaralice.lastIndex)
                else {
                    zbir += 1197
                    izbacio = true
                    break
                }
            } else if (list[i][j] == ']') {
                if (otvaralice.last() == '[') otvaralice.removeAt(otvaralice.lastIndex)
                else {
                    zbir += 57
                    izbacio = true
                    break
                }
            } else if (list[i][j] == '>') {
                if (otvaralice.last() == '<') otvaralice.removeAt(otvaralice.lastIndex)
                else {
                    zbir += 25137
                    izbacio = true
                    break
                }
            }
        }
        if (!izbacio) {
            var zbir2: Long = 0
            for (j in otvaralice.size - 1 downTo 0)
                if (otvaralice[j] == '(') zbir2 = zbir2 * 5 + 1
                else if (otvaralice[j] == '[') zbir2 = zbir2 * 5 + 2
                else if (otvaralice[j] == '{') zbir2 = zbir2 * 5 + 3
                else if (otvaralice[j] == '<') zbir2 = zbir2 * 5 + 4
            zbirovi.add(zbir2)
        }
    }
    println("Part 1: $zbir")
    zbirovi.sort()
    println("Part 2: ${zbirovi[(zbirovi.size - 1) / 2]}")
    println("Execution time: ${System.currentTimeMillis() - start} ms")
}