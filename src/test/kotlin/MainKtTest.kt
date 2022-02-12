import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MainKtTest {

    @Test
    fun testFunkcijePart1() {
        val list = readFile("input2.txt")
        assertEquals(17, funkcijaPart1(list[list.indexOf("") + 1].split(" ").last(), list.subList(0, list.indexOf(""))))
    }

    @Test
    fun testFunkcijePart2() {
        val list = readFile("input2.txt")
        assertEquals(16, funkcijaPart2(list.subList(list.indexOf("")+1, list.size), list.subList(0, list.indexOf(""))))
    }
}