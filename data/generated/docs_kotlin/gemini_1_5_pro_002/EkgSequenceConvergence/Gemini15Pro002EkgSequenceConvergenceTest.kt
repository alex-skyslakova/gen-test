import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EkgSequenceTest {

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

    private fun generateEKG(start: Int, limit: Int): IntArray {
        val ekg = IntArray(limit)
        ekg[0] = 1
        ekg[1] = start
        for (n in 2 until limit) {
            var i = 2
            while (true) {
                if (!ekg.slice(0 until n).contains(i) && gcd(ekg[n - 1], i) > 1) {
                    ekg[n] = i
                    break
                }
                i++
            }
        }
        return ekg
    }

    @Test
    fun testEKG2() {
        val expected = intArrayOf(1, 2, 4, 6, 8, 10, 12, 14, 16, 18)
        val actual = generateEKG(2, 10)
        assertEquals(expected.contentToString(), actual.contentToString())
    }

    @Test
    fun testEKG5() {
        val expected = intArrayOf(1, 5, 10, 2, 4, 6, 8, 12, 14, 16)
        val actual = generateEKG(5, 10)
         assertEquals(expected.contentToString(), actual.contentToString())
    }

    @Test
    fun testEKG7() {
        val expected = intArrayOf(1, 7, 14, 2, 4, 6, 8, 10, 12, 16)
        val actual = generateEKG(7, 10)
        assertEquals(expected.contentToString(), actual.contentToString())
    }

    @Test
    fun testEKG9() {
        val expected = intArrayOf(1, 9, 3, 6, 12, 2, 4, 8, 10, 14)
        val actual = generateEKG(9, 10)
        assertEquals(expected.contentToString(), actual.contentToString())
    }

    @Test
    fun testEKG10() {
        val expected = intArrayOf(1, 10, 2, 4, 6, 8, 12, 14, 16, 18)
        val actual = generateEKG(10, 10)
        assertEquals(expected.contentToString(), actual.contentToString())

    }

    @Test
    fun testConvergenceEKG5andEKG7() {
        val ekg5 = generateEKG(5, 100)
        val ekg7 = generateEKG(7, 100)

        for (i in 2 until 100) {
             if (ekg5[i] == ekg7[i] &&
                ekg5.slice(0 until i).sorted() == ekg7.slice(0 until i).sorted()) {
                 assertEquals(14, i+1) // Converge at term 14 (index 13 + 1)
                return
            }
        }
        assertTrue(false, "EKG(5) and EKG(7) did not converge within 100 terms.")
    }
}
