import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Color

class BasicBitmapStorageTest {

    @Test
    fun testInitialFillColor() {
        val bbs = BasicBitmapStorage(320, 240)
        bbs.fill(Color.white)
        for (x in 0 until 320) {
            for (y in 0 until 240) {
                assertEquals(Color.white, bbs.getPixel(x, y), "Pixel at ($x, $y) should be white")
            }
        }
    }

    @Test
    fun testSetPixelColor() {
        val bbs = BasicBitmapStorage(320, 240)
        bbs.fill(Color.white)
        bbs.setPixel(100, 100, Color.red)
        assertEquals(Color.red, bbs.getPixel(100, 100), "Pixel at (100, 100) should be red")
    }

    @Test
    fun testSetPixelDoesNotAffectOthers() {
        val bbs = BasicBitmapStorage(320, 240)
        bbs.fill(Color.white)
        bbs.setPixel(100, 100, Color.red)
        for (x in 0 until 320) {
            for (y in 0 until 240) {
                if (x != 100 || y != 100) {
                    assertEquals(Color.white, bbs.getPixel(x, y), "Pixel at ($x, $y) should remain white")
                }
            }
        }
    }
}
