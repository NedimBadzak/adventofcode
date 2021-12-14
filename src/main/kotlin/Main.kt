import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import kotlin.math.abs

fun main(args: Array<String>) {
    //Open local file
    val file = File("input.txt")
    val inputStream = file.inputStream()
    val reader = BufferedReader(InputStreamReader(inputStream))

    val lista = reader.readLine().split(",").map { it.toInt() }.toList()

    val sume = mutableListOf<Long>()
    for(i in lista.indices) {
        var sum : Long = 0
        for(j in lista.indices) {
            sum += sum(abs(i - lista[j]).toLong())
        }
        sume.add(sum)

    }

    println(sume.minOrNull())
}

fun sum(n: Long) : Long {
    var suma : Long = 0

    for(i in 0..n) {
        suma += i
    }
    return suma
}

