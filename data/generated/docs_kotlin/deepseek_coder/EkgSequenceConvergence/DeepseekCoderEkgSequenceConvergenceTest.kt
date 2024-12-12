import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class EKGTest {

    @Test
    fun testEKG2() {
        val ekg2 = generateEKG(2)
        val expected = listOf(1, 2, 4, 6, 3, 9, 12, 8, 10, 5)
        assertEquals(expected, ekg2.take(10))
    }

    @Test
    fun testEKG5() {
        val ekg5 = generateEKG(5)
        val expected = listOf(1, 5, 10, 2, 4, 6, 3, 9, 12, 8)
        assertEquals(expected, ekg5.take(10))
    }

    @Test
    fun testEKG7() {
        val ekg7 = generateEKG(7)
        val expected = listOf(1, 7, 14, 2, 4, 6, 3, 9, 12, 8)
        assertEquals(expected, ekg7.take(10))
    }

    @Test
    fun testEKG9() {
        val ekg9 = generateEKG(9)
        val expected = listOf(1, 9, 3, 6, 2, 4, 8, 10, 5, 15)
        assertEquals(expected, ekg9.take(10))
    }

    @Test
    fun testEKG10() {
        val ekg10 = generateEKG(10)
        val expected = listOf(1, 10, 2, 4, 6, 3, 9, 12, 8, 14)
        assertEquals(expected, ekg10.take(10))
    }

    @Test
    fun testEKG5AndEKG7Convergence() {
        val ekg5 = generateEKG(5)
        val ekg7 = generateEKG(7)
        var convergenceIndex = -1

        for (i in 2 until LIMIT) {
            if (ekg5[i] == ekg7[i] && ekg5.take(i).sorted() == ekg7.take(i).sorted()) {
                convergenceIndex = i + 1
                break
            }
        }

        assertEquals(21, convergenceIndex)
    }

    private fun generateEKG(start: Int): List<Int> {
        val ekg = mutableListOf(1, start)
        for (n in 2 until LIMIT) {
            var i = 2
            while (true) {
                if (!ekg.contains(i) && gcd(ekg[n - 1], i) > 1) {
                    ekg.add(i)
                    break
                }
                i++
            }
        }
        return ekg
    }

    private fun gcd(a: Int, b: Int): Int {
        var aa = a
        var bb = b
        while (aa != bb) {
            if (aa > bb)
                aa -= bb
            else
                bb -= aa
        }
        return aa
    }

    companion object {
        const val LIMIT = 100
    }
}
