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
    val pocetni = list[0]

    val time1 = measureTimeMillis {
        println(funkcijaPart1(pocetni, list.subList(2, list.size)))
    }
    println("Vrijeme part 1: $time1 ms")

    val time2 = measureTimeMillis {
        println(funkcijaPart2(pocetni, list.subList(2, list.size)))
    }
    println("Vrijeme part 2: $time2 ms")
}

fun funkcijaPart1(pocetni: String, lista: List<String>): Long {
    var radniPocetni: MutableList<Char> = pocetni.toMutableList()
    var novi: MutableList<Char> = mutableListOf()
    var brojPonavljanjaZnakova: MutableMap<Char, Int> = mutableMapOf()
    for (j in 1..10) {
        for (i in 0..radniPocetni.size - 2) {
            val temp: String = radniPocetni.subList(i, i + 2).joinToString("")

            lista.forEach {
                val kombinacija = it.split(" -> ")
                if (kombinacija[0] == temp) {
                    if (novi.isEmpty()) novi.add(temp[0])
                    novi.add(kombinacija[1].toCharArray()[0])
                    novi.add(temp[1])
                }
            }
        }
        radniPocetni = novi
        novi = mutableListOf()
    }
    return radniPocetni.groupingBy { it }.eachCount()
        .maxByOrNull { it.value }?.value?.toLong()!! - radniPocetni.groupingBy { it }.eachCount()
        .minByOrNull { it.value }?.value?.toLong()!!
}


fun funkcijaPart2(pocetni: String, lista: List<String>) : Long {
    var mapaPonavljanja: MutableMap<Pair<Char, Char>, Long> = mutableMapOf()
    for (i in 0..pocetni.length - 2) {
        val temp: String = pocetni.substring(i, i + 2)
        mapaPonavljanja[Pair(temp[0], temp[1])] = mapaPonavljanja.getOrDefault(Pair(temp[0], temp[1]), 0) + 1
    }
    val novaMapa = mapaPonavljanja.toMutableMap()
    for (i in 1..40) {
        var mapaIterator = mapaPonavljanja.iterator()
        while(mapaIterator.hasNext()) {
            val (prviMapa, drugiMapa) = mapaIterator.next()
            val (prvi, drugi) = lista.find { it.contains(prviMapa.first.toString() + prviMapa.second.toString()) }!!.split(" -> ")
            if(novaMapa.getOrDefault(Pair(prvi[0], prvi[1]), 0L) - drugiMapa == 0L) {
                novaMapa.remove(Pair(prvi[0], prvi[1]))
            } else {
                novaMapa[Pair(prvi[0], prvi[1])] = novaMapa.getOrDefault(Pair(prvi[0], prvi[1]), 0) - drugiMapa
            }
            novaMapa[Pair(prvi[0], drugi[0])] = novaMapa.getOrDefault(Pair(prvi[0], drugi[0]), 0) + drugiMapa
            novaMapa[Pair(drugi[0], prvi[1])] = novaMapa.getOrDefault(Pair(drugi[0], prvi[1]), 0) + drugiMapa;

        }
        mapaPonavljanja = novaMapa.toMutableMap()
    }
//    var stringonja = ""
//    for(i in mapaPonavljanja) {
//        for(j in 1..i.value) {
//            stringonja += i.key.first.toString() + i.key.second.toString()
//        }
//    }
//    println((stringonja.groupingBy { it }.eachCount()
//        .maxByOrNull { it.value }?.value?.toLong()!! - stringonja.groupingBy { it }.eachCount()
//        .minByOrNull { it.value }?.value?.toLong()!! + 1)/2)

    var niz = ('A'..'Z').associateWith { 0L }.toMutableMap()
    for(i in mapaPonavljanja) {
        niz[i.key.first] = niz.getOrDefault(i.key.first, 0L) + i.value
        niz[i.key.second] = niz.getOrDefault(i.key.second, 0L) + i.value
    }
    niz[pocetni[pocetni.length-1]] = niz.getOrDefault(pocetni[pocetni.length-1], 0L) + 1;
     niz = niz.filter{
        it.value != 0L
     }.toMutableMap()
    return (niz.maxByOrNull { it.value }?.value?.toLong()!! - niz.minByOrNull { it.value }?.value?.toLong()!!)/2
}


