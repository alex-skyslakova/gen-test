import kotlin.test.Test
import kotlin.test.assertEquals

class LoopsWithMultipleRangesTest {

    @Test
    fun testPowerFunction() {
        assertEquals(1, 2 pow 0)
        assertEquals(2, 2 pow 1)
        assertEquals(4, 2 pow 2)
        assertEquals(8, 2 pow 3)
        assertEquals(1024, 2 pow 10)
    }

    @Test
    fun testMainFunction() {
        val expectedSum = 10976
        val expectedProd = 0

        var prod = 1
        var sum = 0
        val x = 5
        val y = -5
        val z = -2
        val one = 1
        val three = 3
        val seven = 7
        val p = 11 pow x

        fun process(j: Int) {
            sum += abs(j)
            if (abs(prod) < (1 shl 27) && j != 0) prod *= j
        }

        for (j in -three..(3 pow 3) step three) process(j)
        for (j in -seven..seven step x) process(j)
        for (j in 555..550-y) process(j)
        for (j in 22 downTo -28 step three) process(j)
        for (j in 1927..1939) process(j)
        for (j in x downTo y step -z) process(j)
        for (j in p..p + one) process(j)

        assertEquals(expectedSum, sum, "Sum calculation is incorrect")
        assertEquals(expectedProd, prod, "Product calculation is incorrect")
    }
}
