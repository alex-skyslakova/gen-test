import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class FilterTest {

    @Test
    fun testFilter_emptySignal() {
        val a = doubleArrayOf(1.0, 0.0, 0.0)
        val b = doubleArrayOf(0.5, 0.5)
        val signal = doubleArrayOf()
        val result = filter(a, b, signal)
        assertArrayEquals(doubleArrayOf(), result)
    }

    @Test
    fun testFilter_singleElementSignal() {
        val a = doubleArrayOf(1.0, 0.0, 0.0)
        val b = doubleArrayOf(0.5, 0.5)
        val signal = doubleArrayOf(1.0)
        val result = filter(a, b, signal)
        assertArrayEquals(doubleArrayOf(0.5), result, 0.0001)
    }


    @Test
    fun testFilter_givenExample() {
        val a = doubleArrayOf(1.00000000, -2.77555756e-16, 3.33333333e-01, -1.85037171e-17)
        val b = doubleArrayOf(0.16666667, 0.5, 0.5, 0.16666667)

        val signal = doubleArrayOf(
            -0.917843918645, 0.141984778794, 1.20536903482, 0.190286794412,
            -0.662370894973, -1.00700480494, -0.404707073677, 0.800482325044,
            0.743500089861, 1.01090520172, 0.741527555207, 0.277841675195,
            0.400833448236, -0.2085993586, -0.172842103641, -0.134316096293,
            0.0259303398477, 0.490105989562, 0.549391221511, 0.9047198589
        )

        val expectedResult = doubleArrayOf(
            -0.1530, -0.0584,  0.3816,  0.4215, -0.0789, -0.5332, -0.5339,
             0.0840,  0.4446,  0.7250,  0.7301,  0.5185,  0.4065,  0.0431,
            -0.0945, -0.1039, -0.0078,  0.2088,  0.3648,  0.5735
        )

        val result = filter(a, b, signal)
        assertArrayEquals(expectedResult, result, 0.0001)

    }

    @Test
    fun testFilter_aGreaterThanB() {
        val a = doubleArrayOf(1.0, 0.5, 0.25)
        val b = doubleArrayOf(0.5, 0.5)
        val signal = doubleArrayOf(1.0, 2.0, 3.0)
        val result = filter(a, b, signal)
        assertArrayEquals(doubleArrayOf(0.5, 1.25, 1.8125), result, 0.0001)
    }

    @Test
    fun testFilter_bGreaterThanA() {
        val a = doubleArrayOf(1.0, 0.5)
        val b = doubleArrayOf(0.25, 0.5, 0.25)
        val signal = doubleArrayOf(1.0, 2.0, 3.0)
        val result = filter(a, b, signal)
        assertArrayEquals(doubleArrayOf(0.25, 1.0, 2.0), result, 0.0001)
    }


}
