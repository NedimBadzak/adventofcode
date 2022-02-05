import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MainKtTest {

    @Test
    fun testFunkcijePart1() {
        val list = readFile("input2.txt")
        assertEquals(1588, funkcijaPart1(list[0], list.subList(2, list.size)))
    }

    @Test
    fun testFunkcijePart2() {
        val list = readFile("input2.txt")
        assertEquals(2188189693529, funkcijaPart2(list[0], list.subList(2, list.size)))
    }
}