import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import kotlin.math.abs

fun main(args: Array<String>) {
    //Open local file
    val file = File("input.txt")
    val inputStream = file.inputStream()
    val reader = BufferedReader(InputStreamReader(inputStream))

    //Create a list of zero-based coordinates
    var lista: MutableList<Long> = mutableListOf()

    var niz = (0L..8L).associateWith { 0L }.toMutableMap()
    reader.readLine().split(",").map { it.toLong() }.forEach {
        niz[it] = niz[it]!! + 1
    }


//    lista.forEach { niz[it]++ }
    for(i in 1..256) {
        //Move all elements in a list to the right
        val dayZero = niz[0]
        val dayOne = niz[1]
        val dayTwo = niz[2]
        val dayThree = niz[3]
        val dayFour = niz[4]
        val dayFive = niz[5]
        val daySix = niz[6]
        val daySeven = niz[7]
        val dayEight = niz[8]
        niz[0] = dayOne!!.toLong()
        niz[1] = dayTwo!!.toLong()
        niz[2] = dayThree!!.toLong()
        niz[3] = dayFour!!.toLong()
        niz[4] = dayFive!!.toLong()
        niz[5] = daySix!!.toLong()
        niz[6] = daySeven!!.toLong() + dayZero!!.toLong()
        niz[7] = dayEight!!.toLong()
        niz[8] = dayZero.toLong()
    }

//    println(niz.reduce(Int::plus))
    var zbir : Long = 0
    niz.forEach {
        zbir += it.value
    }
    println(zbir)
}




