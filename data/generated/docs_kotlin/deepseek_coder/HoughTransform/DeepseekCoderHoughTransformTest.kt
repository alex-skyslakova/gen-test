import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.test.assertFailsWith

class HoughTransformTest {

    @Test
    fun testReadInputFromImage() {
        val inputImage = "test_input.png"
        val arrayData = readInputFromImage(inputImage)

        // Assuming the test image is 100x100 pixels
        assertEquals(100, arrayData.width)
        assertEquals(100, arrayData.height)

        // Check some pixel values to ensure they are correctly read
        // Assuming the test image has known pixel values
        assertEquals(128, arrayData[0, 0])
        assertEquals(255, arrayData[50, 50])
        assertEquals(0, arrayData[99, 99])
    }

    @Test
    fun testHoughTransformCalculation() {
        val width = 100
        val height = 100
        val arrayData = ArrayData(width, height)

        // Set some pixel values for testing
        arrayData[0, 0] = 128
        arrayData[50, 50] = 255
        arrayData[99, 99] = 0

        val thetaAxisSize = 180
        val rAxisSize = 200
        val minContrast = 64

        val outputData = arrayData(thetaAxisSize, rAxisSize, minContrast)

        // Check some values in the output data
        // These values depend on the specific implementation and input data
        // You may need to adjust these based on the expected output
        assertEquals(1, outputData[0, 0])
        assertEquals(1, outputData[90, 100])
        assertEquals(1, outputData[179, 199])
    }

    @Test
    fun testContrastCalculation() {
        val width = 100
        val height = 100
        val arrayData = ArrayData(width, height)

        // Set some pixel values for testing
        arrayData[0, 0] = 128
        arrayData[1, 1] = 255
        arrayData[99, 99] = 0

        // Test contrast calculation
        assertTrue(arrayData.contrast(0, 0, 64))
        assertFalse(arrayData.contrast(99, 99, 64))
    }

    @Test
    fun testAccumulate() {
        val width = 100
        val height = 100
        val arrayData = ArrayData(width, height)

        // Initial value should be 0
        assertEquals(0, arrayData[0, 0])

        // Accumulate some values
        arrayData.accumulate(0, 0, 1)
        arrayData.accumulate(0, 0, 2)

        // Check the accumulated value
        assertEquals(3, arrayData[0, 0])
    }

    @Test
    fun testWriteOutputImage() {
        val width = 100
        val height = 100
        val arrayData = ArrayData(width, height)

        // Set some pixel values for testing
        arrayData[0, 0] = 128
        arrayData[50, 50] = 255
        arrayData[99, 99] = 0

        val outputImage = "test_output.png"
        arrayData.writeOutputImage(outputImage)

        // Check if the output image file is created
        assertTrue(File(outputImage).exists())

        // Read the output image and check some pixel values
        val image = ImageIO.read(File(outputImage))
        assertEquals(100, image.width)
        assertEquals(100, image.height)

        // Check some pixel values in the output image
        // These values depend on the specific implementation and input data
        // You may need to adjust these based on the expected output
        assertEquals(0x908080, image.getRGB(0, 99))
        assertEquals(0x90FFFF, image.getRGB(50, 49))
        assertEquals(0x900000, image.getRGB(99, 0))
    }

    @Test
    fun testInvalidInput() {
        // Test invalid input file
        assertFailsWith<Exception> {
            readInputFromImage("non_existent_file.png")
        }
    }
}
