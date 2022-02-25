import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

inline fun measureTimeMillis(block: () -> Unit): Long {
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}

fun readFile(name: String): List<String> {
    val reader = BufferedReader(InputStreamReader(File(name).inputStream()))

    var line = reader.readLine()

    val list: MutableList<String> = mutableListOf()
    while (line != null) {
        list.add(line)
        line = reader.readLine()
    }
    return list
}

fun main() {
    val list = readFile("input.txt")
    val (x1, x2) = list[0].replace(Regex(",|=|[A-Za-z]"), "").split(" ")[2].split("..").toList().map { it.toInt() }
    val poX : Pair<Int, Int> = Pair(x1, x2)
    val (y1, y2) = list[0].replace(Regex(",|=|[A-Za-z]"), "").split(" ")[3].split("..").toList().map { it.toInt() }
    val poY : Pair<Int, Int> = Pair(y1, y2)
    val time1 = measureTimeMillis {
        println(funkcijaPart1(poX, poY))
    }
    println("Vrijeme part 1: $time1 ms")

//    val time2 = measureTimeMillis {
//        println(funkcijaPart2(poX, poY))
//    }
//    println("Vrijeme part 2: $time2 ms")
}

fun funkcijaPart1(poX: Pair<Int, Int>, poY: Pair<Int, Int>): Int {

    return (-poY.first-1)*(-poY.first)/2
}


//fun funkcijaPart2(poX: Pair<Int, Int>, poY: Pair<Int, Int>): Int {

//}



