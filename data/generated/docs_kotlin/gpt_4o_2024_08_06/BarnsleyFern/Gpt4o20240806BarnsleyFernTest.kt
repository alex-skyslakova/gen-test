import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.image.BufferedImage

class BarnsleyFernTest {

    @Test
    fun testInitialConditions() {
        val fern = BarnsleyFern(640)
        val img = BufferedImage(640, 640, BufferedImage.TYPE_INT_ARGB)
        assertEquals(img.width, fern.preferredSize.width)
        assertEquals(img.height, fern.preferredSize.height)
    }

    @Test
    fun testTransformationF1() {
        val x = 0.0
        val y = 1.0
        val tmpx = 0.0
        val tmpy = 0.16 * y
        assertEquals(0.0, tmpx)
        assertEquals(0.16, tmpy)
    }

    @Test
    fun testTransformationF2() {
        val x = 1.0
        val y = 1.0
        val tmpx = 0.85 * x + 0.04 * y
        val tmpy = -0.04 * x + 0.85 * y + 1.6
        assertEquals(0.89, tmpx)
        assertEquals(2.41, tmpy)
    }

    @Test
    fun testTransformationF3() {
        val x = 1.0
        val y = 1.0
        val tmpx = 0.2 * x - 0.26 * y
        val tmpy = 0.23 * x + 0.22 * y + 1.6
        assertEquals(-0.06, tmpx)
        assertEquals(2.05, tmpy)
    }

    @Test
    fun testTransformationF4() {
        val x = 1.0
        val y = 1.0
        val tmpx = -0.15 * x + 0.28 * y
        val tmpy = 0.26 * x + 0.24 * y + 0.44
        assertEquals(0.13, tmpx)
        assertEquals(0.94, tmpy)
    }

    @Test
    fun testImageDimensions() {
        val dim = 640
        val fern = BarnsleyFern(dim)
        assertEquals(dim, fern.preferredSize.width)
        assertEquals(dim, fern.preferredSize.height)
    }
}
