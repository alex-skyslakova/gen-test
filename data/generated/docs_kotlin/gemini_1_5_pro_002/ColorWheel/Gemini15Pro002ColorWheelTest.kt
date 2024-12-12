import org.junit.jupiter.api.Test
import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.test.assertEquals
import kotlin.math.*

class BasicBitmapStorageTest {

    @Test
    fun testFill() {
        val bbs = BasicBitmapStorage(10, 10)
        val fillColor = Color.RED
        bbs.fill(fillColor)
        for (x in 0 until 10) {
            for (y in 0 until 10) {
                assertEquals(fillColor, bbs.getPixel(x, y))
            }
        }
    }

    @Test
    fun testSetAndGetPixel() {
        val bbs = BasicBitmapStorage(10, 10)
        val pixelColor = Color.BLUE
        bbs.setPixel(5, 5, pixelColor)
        assertEquals(pixelColor, bbs.getPixel(5, 5))
    }

    @Test
    fun testColorWheelCenterPixel() {
        val bbs = BasicBitmapStorage(100, 100)
        bbs.colorWheel()
        val centerX = bbs.image.width / 2
        val centerY = bbs.image.height / 2
        // Center pixel should be red (hue = 0)
        val expectedColor = Color.HSBtoRGB(0f, 1f, 1f)
        assertEquals(Color(expectedColor), bbs.getPixel(centerX, centerY))

    }


    @Test
    fun testColorWheelEdgePixels() {
        val bbs = BasicBitmapStorage(100, 100)
        bbs.colorWheel()
        val centerX = bbs.image.width / 2
        val centerY = bbs.image.height / 2
        val radius = minOf(centerX, centerY)

        // Test a few points on the edge of the circle
        val testPoints = listOf(
            Pair(0.0, 1.0), // Top
            Pair(1.0, 0.0), // Right
            Pair(0.0, -1.0), // Bottom
            Pair(-1.0, 0.0) // Left

        )

        for ((dx_normalized, dy_normalized) in testPoints) {
            val dx = dx_normalized * radius
            val dy = dy_normalized * radius
            val x = (centerX + dx).toInt()
            val y = (centerY + dy).toInt()

             val theta = atan2(dy, dx)
            val hue = (theta + PI) / (2.0 * PI)
            val expectedRGB = Color.HSBtoRGB(hue.toFloat(), 1.0f, 1.0f)

            val actualColor = bbs.getPixel(x,y)

            // Due to potential rounding errors in color conversion, check RGB values with a small tolerance
            val tolerance = 5

            val rDiff = abs(actualColor.red - Color(expectedRGB).red)
            val gDiff = abs(actualColor.green - Color(expectedRGB).green)
            val bDiff = abs(actualColor.blue - Color(expectedRGB).blue)

            assert(rDiff <= tolerance)
            assert(gDiff <= tolerance)
            assert(bDiff <= tolerance)


        }

    }

    @Test
    fun testColorWheelOutsideCircle() {
        val bbs = BasicBitmapStorage(100, 100)
        bbs.fill(Color.BLACK) // Initialize with black
        bbs.colorWheel()
        // Point outside the circle should remain black
        assertEquals(Color.BLACK, bbs.getPixel(0, 0))
    }




}
