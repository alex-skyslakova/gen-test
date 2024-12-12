import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class FivenumTest {

    @Test
    fun testFivenum_example1() {
        val x = doubleArrayOf(15.0, 6.0, 42.0, 41.0, 7.0, 36.0, 49.0, 40.0, 39.0, 47.0, 43.0)
        val expected = doubleArrayOf(6.0, 15.0, 40.0, 43.0, 49.0)
        assertEquals(expected.asList(), fivenum(x).asList())
    }

    @Test
    fun testFivenum_example2() {
        val x = doubleArrayOf(36.0, 40.0, 7.0, 39.0, 41.0, 15.0)
        val expected = doubleArrayOf(7.0, 15.0, 37.5, 40.0, 41.0)
        assertEquals(expected.asList(), fivenum(x).asList())
    }

    @Test
    fun testFivenum_example3() {
        val x = doubleArrayOf(
            0.14082834,  0.09748790,  1.73131507,  0.87636009, -1.95059594,  0.73438555,
            -0.03035726,  1.46675970, -0.74621349, -0.72588772,  0.63905160,  0.61501527,
            -0.98983780, -1.00447874, -0.62759469,  0.66206163,  1.04312009, -0.10305385,
            0.75775634,  0.32566578
        )
        val expected = doubleArrayOf(-1.95059594, -0.736050605, 0.30566053, 0.746056035, 1.73131507)
        assertEquals(expected.asList(), fivenum(x).asList())

    }


    @Test
    fun testFivenum_singleElement() {
        val x = doubleArrayOf(5.0)
        val expected = doubleArrayOf(5.0, 5.0, 5.0, 5.0, 5.0)
        assertEquals(expected.asList(), fivenum(x).asList())
    }

    @Test
    fun testFivenum_twoElements() {
        val x = doubleArrayOf(5.0, 10.0)
        val expected = doubleArrayOf(5.0, 5.0, 7.5, 10.0, 10.0)
        assertEquals(expected.asList(), fivenum(x).asList())
    }

    @Test
    fun testFivenum_emptyArray() {
        assertThrows<IllegalArgumentException> { fivenum(doubleArrayOf()) }
    }

    @Test
    fun testFivenum_withNaN() {
        assertThrows<IllegalArgumentException> { fivenum(doubleArrayOf(1.0, 2.0, Double.NaN)) }
    }

    @Test
    fun testMedian_emptyArray() {
        assertThrows<IllegalArgumentException> { median(doubleArrayOf(), 0, -1) }
    }

}
