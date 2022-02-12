import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.*

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
    val pocetni = list[list.indexOf("") + 1].split(" ").last()

    val time1 = measureTimeMillis {
        println(funkcijaPart1(pocetni, list.subList(0, list.indexOf(""))))
    }
    println("Vrijeme part 1: $time1 ms")

    val time2 = measureTimeMillis {
        funkcijaPart2(list.subList(list.indexOf("")+1, list.size), list.subList(0, list.indexOf("")))
    }
    println("Vrijeme part 2: $time2 ms")
}

fun funkcijaPart1(pocetni: String, lista: List<String>): Int {
    //Naci maksimalni broj u listi i napraviti listu te velicine i onda samo dole trpati lista[jedan][dva]
    val maxI = lista.map { it.split(",").first().toInt() }.maxOrNull()!!
    val maxJ = lista.map { it.split(",").last().toInt() }.maxOrNull()!!
    var niz = MutableList(maxJ + 1) { MutableList(maxI + 1) { 0 } }

    lista.forEach {
        val (prvi, drugi) = it.split(",")
        niz[drugi.toInt()][prvi.toInt()] = 1
    }

    if (pocetni.contains("y=")) {
        val y = pocetni.split("y=").last().toInt()
        for (i in y + 1..maxJ) {
            for (j in 0..maxI) {
                if (niz[i][j] == 1) {
                    niz[i][j] = 0
                    niz[y - (i - y)][j] = 1
                }
            }
        }
        for (i in y..maxJ) {
            niz.removeAt(y)
        }
    } else {
        val x = pocetni.split("x=").last().toInt()
        for (i in 0..maxJ) {
            for (j in x + 1..maxI) {
                if (niz[i][j] == 1) {
                    niz[i][j] = 0
                    niz[i][x - (j - x)] = 1
                }
            }
        }
    }
    //Return the number of 1s in the final matrix
    return niz.flatten().count { it == 1 }
}


fun funkcijaPart2(pocetniLista: List<String>, lista: List<String>): Int {
    //Naci maksimalni broj u listi i napraviti listu te velicine i onda samo dole trpati lista[jedan][dva]
    var maxI = lista.map { it.split(",").first().toInt() }.maxOrNull()!!
    var maxJ = lista.map { it.split(",").last().toInt() }.maxOrNull()!!
    var niz = MutableList(maxJ + 1) { MutableList(maxI + 1) { 0 } }

    lista.forEach {
        val (prvi, drugi) = it.split(",")
        niz[drugi.toInt()][prvi.toInt()] = 1
    }
    pocetniLista.forEach { pocetni: String ->
        if (pocetni.contains("y=")) {
            val y = pocetni.split("y=").last().toInt()
            for (i in y + 1..maxJ) {
                for (j in 0..maxI) {
                    if (niz[i][j] == 1) {
                        niz[i][j] = 0
                        niz[y - (i - y)][j] = 1
                    }
                }
            }
            for (i in y..maxJ) {
                niz.removeAt(y)
            }
            maxJ = niz.size - 1
        } else {
            val x = pocetni.split("x=").last().toInt()
            for (i in 0..maxJ) {
                for (j in x + 1..maxI) {
                    if (niz[i][j] == 1) {
                        niz[i][j] = 0
                        niz[i][x - (j - x)] = 1
                    }
                }
            }
            for (i in 0..maxJ) {
                for(j in x..maxI - 1){
                    niz[i].removeAt(x)
                }
            }
            maxI = niz.map { it.size - 1 }.maxOrNull()!!
        }
    }
    for(i in 0..niz.size-1){
        for(j in 0..niz[i].size-1){
           if(niz[i][j] == 1){
               print("#")
           } else {
               print(" ")
           }
        }
        println()
    }
    //Return the number of 1s in the final matrix
    return niz.flatten().count { it == 1 }
}



