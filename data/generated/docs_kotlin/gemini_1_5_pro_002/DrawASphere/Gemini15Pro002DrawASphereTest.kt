import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class DrawSphereTest {

    @Test
    fun testNormalize() {
        val v = doubleArrayOf(3.0, 4.0, 0.0)
        normalize(v)
        assertArrayEquals(doubleArrayOf(0.6, 0.8, 0.0), v, 0.0001)
    }

    @Test
    fun testDotProductPositive() {
        val x = doubleArrayOf(1.0, 0.0, 0.0)
        val y = doubleArrayOf(0.5, 0.0, 0.0)
        assertEquals(0.0, dot(x, y), 0.0001)
    }
     @Test
    fun testDotProductNegative() {
        val x = doubleArrayOf(1.0, 0.0, 0.0)
        val y = doubleArrayOf(-0.5, 0.0, 0.0)
        assertEquals(0.5, dot(x, y), 0.0001)
    }

    @Test
    fun testDrawSphereSmallRadius() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        normalize(light) // Important: Call normalize before drawSphere in tests
        drawSphere(3.0, 1.0, 0.1) 

        val expectedOutput = """
       *
    *** *
   *****
  *******
 *********
  *******
   *****
    *** *
       *

""".trimIndent()

        assertEquals(expectedOutput, outputStream.toString().trim())
           System.setOut(System.out) // Resetting standard output
    }
    @Test
    fun testDrawSphereZeroRadius() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        normalize(light) 
        drawSphere(0.0, 1.0, 0.1) 

        val expectedOutput = "*"

        assertEquals(expectedOutput, outputStream.toString().trim())
           System.setOut(System.out) 
    }


}
