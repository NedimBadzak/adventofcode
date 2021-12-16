import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

// 2 i 5 su  duzine 5, razlikuju se u 2, izmaju zajednicka 3, a 2 i 3 i 3 i 5 su zajednickih 4
//Svi osim 0, 1 i 7 imaju red ddd
//6 i 9 i 0 imaju po 6 i razlikuju se u 1
//1 4 7 8 zna se

//3 ima sa 1 sve zajednicke
//2 sa 1 ima 1 zajednicko
//5 sa 1 ima jedno zajednicko
//2 sa 4 ima  2 zajednicko
//5 sa 4 iam 3 zajednicko
//5 sa 6 ima sve iste, samo 6 ima jedan visak
//5 sa 9 ima sve iste, samo 9 ima jedan visak
//5 sa 8 ima sve iste, samo 8 ima 2 viska
//6 sa 8 ima sve iste, samo 8 ima 1 viska
//9 sa 8 ima sve iste, samo 8 ima 1 viska
//6 i 9 se razlikuju time sto 9 sadrzi 7

fun main() {

    val reader = BufferedReader(InputStreamReader(File("input.txt").inputStream()))
    var line = reader.readLine()


    val mapa: MutableMap<List<String>, List<String>> = mutableMapOf()
    while (line != null) {
        val lista1: List<String> = line.split(" | ").toList()[0].split(" ").toList()
        val lista2: List<String> = line.split(" | ").toList()[1].split(" ").toList()
        mapa.put(lista1, lista2)

        line = reader.readLine()
    }


    var suma = 0
    val count = Array(9) { 0 }

    for (i in 0 until mapa.entries.size) mapa.entries.toList()[i].value.forEach { count[it.length]++ }
    println("Part 1 : ${count[2] + count[4] + count[3] + count[7]}")


    for (i in 0 until mapa.entries.size) {
        val mapaBrojZnakovi = mutableMapOf<Int, MutableSet<Char>>()


        mapa.entries.toList()[i].key.forEach {
            count[it.length]++
            if (it.length == 2) mapaBrojZnakovi.put(1, it.toSet() as MutableSet<Char>)
            else if (it.length == 3) mapaBrojZnakovi.put(7, it.toSet() as MutableSet<Char>)
            else if (it.length == 4) mapaBrojZnakovi.put(4, it.toSet() as MutableSet<Char>)
            else if (it.length == 7) mapaBrojZnakovi.put(8, it.toSet() as MutableSet<Char>)
        }

        mapa.entries.toList()[i].key.forEach {
            if (it.length == 5) {
                val temp = it.toSet()
                var sadStavio = false
                if (mapaBrojZnakovi.containsKey(7) && temp.intersect(mapaBrojZnakovi.getValue(7)).size == 3) {
                        mapaBrojZnakovi.put(3, it.toMutableList().toMutableSet())
                        sadStavio = true
                }
                if (!sadStavio && mapaBrojZnakovi.containsKey(4)) {
                    if (temp.intersect(mapaBrojZnakovi.getValue(4)).size == 3) {
                        mapaBrojZnakovi.put(5, it.toMutableList().toMutableSet())
                    } else if (temp.intersect(mapaBrojZnakovi.getValue(4)).size == 2) {
                        mapaBrojZnakovi.put(2, it.toMutableList().toMutableSet())
                    }
                }
            }
            if (it.length == 6) {
                val temp = it.toSet()
                if (mapaBrojZnakovi.containsKey(1)) {
                    if (temp.intersect(mapaBrojZnakovi.getValue(1)).size == 2) {
                        if (mapaBrojZnakovi.containsKey(3)) {
                            if (temp.intersect(mapaBrojZnakovi.getValue(3)).size == 5) {
                                mapaBrojZnakovi.put(9, it.toMutableList().toMutableSet())
                            } else if (temp.intersect(mapaBrojZnakovi.getValue(3)).size == 4) {
                                mapaBrojZnakovi.put(0, it.toMutableList().toMutableSet())
                            }
                        } else if (mapaBrojZnakovi.containsKey(5)) {
                            if (temp.intersect(mapaBrojZnakovi.getValue(5)).size == 5) {
                                mapaBrojZnakovi.put(9, it.toMutableList().toMutableSet())
                            } else if (temp.intersect(mapaBrojZnakovi.getValue(5)).size == 4) {
                                mapaBrojZnakovi.put(0, it.toMutableList().toMutableSet())
                            }
                        } else if (mapaBrojZnakovi.containsKey(4)) {
                            if (temp.intersect(mapaBrojZnakovi.getValue(4)).size == 4) {
                                mapaBrojZnakovi.put(9, it.toMutableList().toMutableSet())
                            } else if (temp.intersect(mapaBrojZnakovi.getValue(4)).size == 3) {
                                mapaBrojZnakovi.put(0, it.toMutableList().toMutableSet())
                            }
                        }
                    } else if (temp.intersect(mapaBrojZnakovi.getValue(1)).size == 1) {
                        mapaBrojZnakovi.put(6, it.toMutableList().toMutableSet())
                    }
                }
            }
        }
        var stringonja = ""
        mapa.entries.toList()[i].value.forEach { v: String ->
            if (mapaBrojZnakovi.values.contains(v.toSet())) {
                stringonja += mapaBrojZnakovi.filter { it.value == v.toSet() }.keys.toList()[0]
            }
        }
        if (stringonja.isNotEmpty()) {
            suma += stringonja.toInt()
        }
    }

    println("Part 2 : $suma")
}


