import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.awt.Color

class BasicBitmapStorageTest {

    @Test
    fun testFill() {
        val width = 100
        val height = 100
        val bbs = BasicBitmapStorage(width, height)
        val fillColor = Color.RED
        bbs.fill(fillColor)

        for (x in 0 until width) {
            for (y in 0 until height) {
                assertEquals(fillColor, bbs.getPixel(x, y), "Pixel at ($x, $y) should be filled with $fillColor")
            }
        }
    }

    @Test
    fun testSetAndGetPixel() {
        val bbs = BasicBitmapStorage(10, 10)
        val color = Color.BLUE
        bbs.setPixel(5, 5, color)
        assertEquals(color, bbs.getPixel(5, 5), "Pixel at (5, 5) should be $color")
    }

    @Test
    fun testColorWheel() {
        val width = 100
        val height = 100
        val bbs = BasicBitmapStorage(width, height)
        bbs.colorWheel()

        val centerX = width / 2
        val centerY = height / 2
        val radius = minOf(centerX, centerY)

        for (y in 0 until height) {
            for (x in 0 until width) {
                val dx = (x - centerX).toDouble()
                val dy = (y - centerY).toDouble()
                val dist = sqrt(dx * dx + dy * dy)

                if (dist <= radius) {
                    val theta = atan2(dy, dx)
                    val hue = (theta + Math.PI) / (2.0 * Math.PI)
                    val expectedColor = Color(Color.HSBtoRGB(hue.toFloat(), 1.0f, 1.0f))
                    assertEquals(expectedColor, bbs.getPixel(x, y), "Pixel at ($x, $y) should have hue $hue")
                }
            }
        }
    }
}
