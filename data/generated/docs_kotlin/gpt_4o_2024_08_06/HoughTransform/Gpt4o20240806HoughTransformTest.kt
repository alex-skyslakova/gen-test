import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File
import javax.imageio.ImageIO

class HoughTransformTest {

    @Test
    fun testArrayDataInitialization() {
        val width = 10
        val height = 10
        val arrayData = ArrayData(width, height)

        assertEquals(width, arrayData.width)
        assertEquals(height, arrayData.height)
        assertEquals(width * height, arrayData.dataArray.size)
    }

    @Test
    fun testArrayDataGetSet() {
        val arrayData = ArrayData(10, 10)
        arrayData[5, 5] = 255

        assertEquals(255, arrayData[5, 5])
    }

    @Test
    fun testContrastFunction() {
        val arrayData = ArrayData(3, 3)
        arrayData[0, 0] = 100
        arrayData[1, 0] = 150
        arrayData[2, 0] = 100
        arrayData[0, 1] = 150
        arrayData[1, 1] = 100
        arrayData[2, 1] = 150
        arrayData[0, 2] = 100
        arrayData[1, 2] = 150
        arrayData[2, 2] = 100

        assertTrue(arrayData.contrast(1, 1, 40))
        assertFalse(arrayData.contrast(1, 1, 60))
    }

    @Test
    fun testHoughTransform() {
        val width = 5
        val height = 5
        val arrayData = ArrayData(width, height)
        for (x in 0 until width) {
            for (y in 0 until height) {
                arrayData[x, y] = if (x == y) 255 else 0
            }
        }

        val result = arrayData(180, 180, 50)
        assertNotNull(result)
        assertEquals(180, result.width)
        assertEquals(180, result.height)
    }

    @Test
    fun testReadInputFromImage() {
        val testImageFile = "test_image.png"
        val image = BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB)
        ImageIO.write(image, "PNG", File(testImageFile))

        val arrayData = readInputFromImage(testImageFile)
        assertEquals(10, arrayData.width)
        assertEquals(10, arrayData.height)

        File(testImageFile).delete()
    }

    @Test
    fun testWriteOutputImage() {
        val arrayData = ArrayData(10, 10)
        for (x in 0 until 10) {
            for (y in 0 until 10) {
                arrayData[x, y] = 255
            }
        }

        val outputFileName = "output_image.png"
        arrayData.writeOutputImage(outputFileName)

        val outputFile = File(outputFileName)
        assertTrue(outputFile.exists())
        outputFile.delete()
    }
}
