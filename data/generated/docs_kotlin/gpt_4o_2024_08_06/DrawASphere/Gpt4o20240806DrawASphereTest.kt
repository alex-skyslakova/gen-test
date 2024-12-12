import kotlin.test.Test
import kotlin.test.assertEquals

class DrawASphereTest {

    @Test
    fun testNormalize() {
        val vector = doubleArrayOf(3.0, 4.0, 0.0)
        normalize(vector)
        val expected = doubleArrayOf(0.6, 0.8, 0.0)
        assertEquals(expected[0], vector[0], 0.0001)
        assertEquals(expected[1], vector[1], 0.0001)
        assertEquals(expected[2], vector[2], 0.0001)
    }

    @Test
    fun testDotProductPositive() {
        val vector1 = doubleArrayOf(1.0, 0.0, 0.0)
        val vector2 = doubleArrayOf(1.0, 0.0, 0.0)
        val result = dot(vector1, vector2)
        assertEquals(1.0, result, 0.0001)
    }

    @Test
    fun testDotProductNegative() {
        val vector1 = doubleArrayOf(1.0, 0.0, 0.0)
        val vector2 = doubleArrayOf(-1.0, 0.0, 0.0)
        val result = dot(vector1, vector2)
        assertEquals(0.0, result, 0.0001)
    }

    @Test
    fun testDotProductZero() {
        val vector1 = doubleArrayOf(1.0, 0.0, 0.0)
        val vector2 = doubleArrayOf(0.0, 1.0, 0.0)
        val result = dot(vector1, vector2)
        assertEquals(0.0, result, 0.0001)
    }

    @Test
    fun testDrawSphere() {
        // This test will check if the drawSphere function runs without errors
        // and produces a non-empty output for given parameters.
        // Note: This test does not verify the correctness of the ASCII art.
        normalize(light)
        drawSphere(5.0, 2.0, 0.3)
        // Since drawSphere prints to the console, we cannot capture its output directly
        // in a unit test without redirecting the standard output.
        // This test is primarily to ensure there are no runtime errors.
    }
}
