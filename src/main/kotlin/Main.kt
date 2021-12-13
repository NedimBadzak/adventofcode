import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import kotlin.math.abs

fun main(args: Array<String>) {
    //Open local file
    val file = File("input.txt")
    val inputStream = file.inputStream()
    val reader = BufferedReader(InputStreamReader(inputStream))
    var line = reader.readLine()

    //Create a list of zero-based coordinates
    var matrica: MutableList<MutableList<Int>> = mutableListOf()

    while (line != null) {
        val row = line.replace(Regex("\\D"), " ")
            .split(Regex(" "))
            .toMutableList()
            .filter { it.isNotEmpty() }
            .map { it.toInt() }

        matrica = provjeriVelicinuMatrice(matrica, row)
        val lowerX = if (row[0] > row[2]) row[2] else row[0]
        val upperX = if (row[0] > row[2]) row[0] else row[2]
        val lowerY = if (row[1] > row[3]) row[3] else row[1]
        val upperY = if (row[1] > row[3]) row[1] else row[3]

        if (row[0] == row[2]) for (i in lowerY..upperY) matrica[i][row[0]] += 1
        else if (row[1] == row[3]) for (i in lowerX..upperX) matrica[row[1]][i] += 1
        //PART 2
        else if (abs(row[0] - row[2]) == abs(row[1] - row[3])) {

            if(row[0] < row[2] && row[1] < row[3])
                for (i in 0.. abs(lowerX - upperX)) matrica[lowerY + i][lowerX + i] += 1
            else if (row[0] > row[2] && row[1] > row[3])
                for (i in abs(lowerX - upperX) downTo 0) matrica[lowerY + i][lowerX + i] += 1
            else if (row[0] < row[2] && row[1] > row[3])
                for (i in 0.. abs(lowerX - upperX)) matrica[upperY - i][lowerX + i] += 1
            else if (row[0] > row[2] && row[1] < row[3])
                for (i in abs(lowerX - upperX) downTo 0) matrica[upperY - i][lowerX + i] += 1
        }
        /////////END PART 2

        line = reader.readLine()
    }

    var max = 0
    for (i in 0 until matrica.size) {
        for (j in 0 until matrica[i].size) {
            if (matrica[i][j] >= 2) max++
        }
    }

    println(max)
}

fun provjeriVelicinuMatrice(matrica: MutableList<MutableList<Int>>, row: List<Int>): MutableList<MutableList<Int>> {
    if (matrica.size - 1 < row[1]) {
        //Add new row
        for (i in matrica.size..row[1]) {
            matrica.add(mutableListOf())
            for (j in 0..matrica[0].size - 1) {
                matrica[i].add(0)
            }
        }

    }
    if (matrica.size - 1 < row[3]) {
        //Add new row
        for (i in matrica.size..row[3]) {
            matrica.add(mutableListOf())
            for (j in 0..matrica[0].size - 1) {
                matrica.add(mutableListOf(0))
            }
        }
    }
    //Check if matrica size is less than row[1]
    if (matrica[0].size - 1 < row[0]) {
        //Add new column
        for (i in 0..matrica.size - 1) {
            for (j in matrica[i].size..row[0]) {
                matrica[i].add(0)
            }
        }
    }
    if (matrica[0].size - 1< row[2]) {
        //Add new column
        for (i in 0..matrica.size - 1) {
            for (j in matrica[i].size..row[2]) {
                matrica[i].add(0)
            }
        }
    }
    return matrica
}


