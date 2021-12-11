import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

fun main(args: Array<String>) {
    //Open local file
    val file = File("input.txt")
    val inputStream = file.inputStream()
    val reader = BufferedReader(InputStreamReader(inputStream))
    val firstLine = reader.readLine().split(",")
    var line = reader.readLine()

    var matrica: MutableList<List<String>> = mutableListOf()

    val matrice: MutableList<MutableList<List<String>>> = mutableListOf()
    var first = true
    while (line != null) {
        if (line.isNotEmpty()) {
            val secondLine = line.split(" ")
            matrica.add(secondLine.filter { it.isNotEmpty() })
        } else if (!first) {
            matrice.add(matrica)
            matrica = mutableListOf()
        } else first = false;
        line = reader.readLine()
    }
    if (matrica.isNotEmpty()) matrice.add(matrica)


    val matricePogadjalica: MutableList<MutableList<MutableList<Boolean>>> = mutableListOf()
    for (i in 0 until matrice.size) {
        val pogadjalica = mutableListOf(
            mutableListOf(false, false, false, false, false),
            mutableListOf(false, false, false, false, false),
            mutableListOf(false, false, false, false, false),
            mutableListOf(false, false, false, false, false),
            mutableListOf(false, false, false, false, false)
        )
        matricePogadjalica.add(pogadjalica);
    }

    var rezultat = 0;
    for (l in 0 until firstLine.size) {
        for (i in 0 until matrice.size) {
            for (j in 0 until matrice[i].size) {
                for (k in 0 until matrice[i][j].size) {
                    if (matrice[i][j][k] == firstLine[l]) {
                        matricePogadjalica[i][j][k] = true;
                        if (provjeriRed(matricePogadjalica, i, j) != -1) {
                            rezultat = saberiMatricu(matricePogadjalica, i, matrice) * firstLine[l].toInt()
                            println(rezultat)
                            return;
                        }
                    }
                }
            }
        }
    }

    println(rezultat);
}

fun saberiMatricu(
    matricePogadjalica: MutableList<MutableList<MutableList<Boolean>>>,
    i: Int,
    matrice: MutableList<MutableList<List<String>>>
): Int {
    var rezultat = 0;
    for (j2 in 0 until matrice[i].size) {
        for (k2 in 0 until matrice[i][j2].size) {
            if (!matricePogadjalica[i][j2][k2]) {
                rezultat += matrice[i][j2][k2].toInt()
            }
        }
    }
    return rezultat;
}

fun provjeriRed(matricePogadjalica: MutableList<MutableList<MutableList<Boolean>>>, i: Int, j: Int): Int {
    var kolona = true
    for(j2 in 0 until matricePogadjalica[i].size) {
        if(!matricePogadjalica[i][j2][0]) kolona = false;
    }
    if (matricePogadjalica[i][j] == mutableListOf(true, true, true, true, true) || kolona) return i;
    return -1;
}

