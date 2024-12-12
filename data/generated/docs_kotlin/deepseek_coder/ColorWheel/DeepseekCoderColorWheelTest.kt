import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.awt.Color
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.sqrt

class BasicBitmapStorageTest {

    private lateinit var bbs: BasicBitmapStorage

    @BeforeEach
    fun setUp() {
        bbs = BasicBitmapStorage(480, 480)
    }

    @Test
    fun testFill() {
        bbs.fill(Color.RED)
        for (y in 0 until bbs.image.height) {
            for (x in 0 until bbs.image.width) {
                assertEquals(Color.RED, bbs.getPixel(x, y))
            }
        }
    }

    @Test
    fun testSetPixel() {
        bbs.setPixel(100, 100, Color.GREEN)
        assertEquals(Color.GREEN, bbs.getPixel(100, 100))
    }

    @Test
    fun testGetPixel() {
        bbs.setPixel(200, 200, Color.BLUE)
        assertEquals(Color.BLUE, bbs.getPixel(200, 200))
    }

    @Test
    fun testColorWheel() {
        bbs.colorWheel()

        val centerX = bbs.image.width / 2
        val centerY = bbs.image.height / 2
        val radius = minOf(centerX, centerY)

        // Test a few points within the circle
        val testPoints = listOf(
            Pair(centerX, centerY), // Center point
            Pair(centerX + radius - 1, centerY), // Right edge
            Pair(centerX - radius + 1, centerY), // Left edge
            Pair(centerX, centerY + radius - 1), // Bottom edge
            Pair(centerX, centerY - radius + 1)  // Top edge
        )

        for ((x, y) in testPoints) {
            val dx = (x - centerX).toDouble()
            val dy = (y - centerY).toDouble()
            val dist = sqrt(dx * dx + dy * dy)
            if (dist <= radius) {
                val theta = atan2(dy, dx)
                val hue = (theta + PI) / (2.0 * PI)
                val expectedColor = Color.getHSBColor(hue.toFloat(), 1.0f, 1.0f)
                assertEquals(expectedColor, bbs.getPixel(x, y))
            }
        }

        // Test a point outside the circle
        val outsidePoint = Pair(centerX + radius + 1, centerY)
        assertEquals(Color.WHITE, bbs.getPixel(outsidePoint.first, outsidePoint.second))
    }
}
