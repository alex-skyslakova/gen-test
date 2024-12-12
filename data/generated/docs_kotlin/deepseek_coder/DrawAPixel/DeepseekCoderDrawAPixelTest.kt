import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.awt.Color

class BasicBitmapStorageTest {

    private lateinit var bbs: BasicBitmapStorage

    @BeforeEach
    fun setUp() {
        bbs = BasicBitmapStorage(320, 240)
    }

    @Test
    fun testFill() {
        bbs.fill(Color.white)
        for (x in 0 until 320) {
            for (y in 0 until 240) {
                assertEquals(Color.white, bbs.getPixel(x, y))
            }
        }
    }

    @Test
    fun testSetPixel() {
        bbs.fill(Color.white)
        bbs.setPixel(100, 100, Color.red)
        assertEquals(Color.red, bbs.getPixel(100, 100))
    }

    @Test
    fun testGetPixel() {
        bbs.fill(Color.white)
        bbs.setPixel(100, 100, Color.red)
        val color = bbs.getPixel(100, 100)
        assertEquals(Color.red, color)
    }

    @Test
    fun testSetPixelOutsideBounds() {
        assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            bbs.setPixel(320, 240, Color.red)
        }
    }

    @Test
    fun testGetPixelOutsideBounds() {
        assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            bbs.getPixel(320, 240)
        }
    }

    @Test
    fun testSetPixelNegativeCoordinates() {
        assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            bbs.setPixel(-1, -1, Color.red)
        }
    }

    @Test
    fun testGetPixelNegativeCoordinates() {
        assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            bbs.getPixel(-1, -1)
        }
    }
}
