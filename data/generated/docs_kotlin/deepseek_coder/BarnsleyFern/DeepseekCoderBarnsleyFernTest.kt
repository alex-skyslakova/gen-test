import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.awt.image.BufferedImage

class BarnsleyFernTest {

    @Test
    fun testCreateFern_InitialPosition() {
        val dim = 640
        val fern = BarnsleyFern(dim)
        val img = fern.img

        // Check initial position (0, 0)
        val initialX = dim / 2
        val initialY = dim
        assertEquals(0xFF32CD32.toInt(), img.getRGB(initialX, initialY))
    }

    @Test
    fun testCreateFern_TransformationF1() {
        val dim = 640
        val fern = BarnsleyFern(dim)
        val img = fern.img

        // Simulate transformation F1
        var x = 0.0
        var y = 0.0
        var tmpx: Double
        var tmpy: Double
        val r = 0.005 // within 1% range
        if (r <= 0.01) {
            tmpx = 0.0
            tmpy = 0.16 * y
        } else {
            fail("Test setup error: r should be within 1% range")
        }
        x = tmpx
        y = tmpy

        val expectedX = Math.round(dim / 2.0 + x * dim / 11.0).toInt()
        val expectedY = Math.round(dim - y * dim / 11.0).toInt()
        assertEquals(0xFF32CD32.toInt(), img.getRGB(expectedX, expectedY))
    }

    @Test
    fun testCreateFern_TransformationF2() {
        val dim = 640
        val fern = BarnsleyFern(dim)
        val img = fern.img

        // Simulate transformation F2
        var x = 0.0
        var y = 0.0
        var tmpx: Double
        var tmpy: Double
        val r = 0.5 // within 85% range
        if (r <= 0.86) {
            tmpx = 0.85 * x + 0.04 * y
            tmpy = -0.04 * x + 0.85 * y + 1.6
        } else {
            fail("Test setup error: r should be within 85% range")
        }
        x = tmpx
        y = tmpy

        val expectedX = Math.round(dim / 2.0 + x * dim / 11.0).toInt()
        val expectedY = Math.round(dim - y * dim / 11.0).toInt()
        assertEquals(0xFF32CD32.toInt(), img.getRGB(expectedX, expectedY))
    }

    @Test
    fun testCreateFern_TransformationF3() {
        val dim = 640
        val fern = BarnsleyFern(dim)
        val img = fern.img

        // Simulate transformation F3
        var x = 0.0
        var y = 0.0
        var tmpx: Double
        var tmpy: Double
        val r = 0.90 // within 7% range
        if (r <= 0.93) {
            tmpx = 0.2 * x - 0.26 * y
            tmpy = 0.23 * x + 0.22 * y + 1.6
        } else {
            fail("Test setup error: r should be within 7% range")
        }
        x = tmpx
        y = tmpy

        val expectedX = Math.round(dim / 2.0 + x * dim / 11.0).toInt()
        val expectedY = Math.round(dim - y * dim / 11.0).toInt()
        assertEquals(0xFF32CD32.toInt(), img.getRGB(expectedX, expectedY))
    }

    @Test
    fun testCreateFern_TransformationF4() {
        val dim = 640
        val fern = BarnsleyFern(dim)
        val img = fern.img

        // Simulate transformation F4
        var x = 0.0
        var y = 0.0
        var tmpx: Double
        var tmpy: Double
        val r = 0.95 // within 7% range
        if (r <= 1.0) {
            tmpx = -0.15 * x + 0.28 * y
            tmpy = 0.26 * x + 0.24 * y + 0.44
        } else {
            fail("Test setup error: r should be within 7% range")
        }
        x = tmpx
        y = tmpy

        val expectedX = Math.round(dim / 2.0 + x * dim / 11.0).toInt()
        val expectedY = Math.round(dim - y * dim / 11.0).toInt()
        assertEquals(0xFF32CD32.toInt(), img.getRGB(expectedX, expectedY))
    }
}
