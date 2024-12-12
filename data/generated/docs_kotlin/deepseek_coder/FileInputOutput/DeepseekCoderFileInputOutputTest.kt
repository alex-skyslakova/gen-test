import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import java.io.File
import kotlin.test.assertEquals

class FileInputOutputTest {

    private val inputFileName = "input.txt"
    private val outputFileName = "output.txt"
    private val testContent = "This is a test content."

    @BeforeEach
    fun setUp() {
        // Create a temporary input file with test content
        File(inputFileName).writeText(testContent)
    }

    @AfterEach
    fun tearDown() {
        // Clean up the files after each test
        File(inputFileName).delete()
        File(outputFileName).delete()
    }

    @Test
    fun `test file input and output`() {
        // Run the main function to perform the file operations
        main(arrayOf())

        // Read the content of the output file
        val outputContent = File(outputFileName).readText()

        // Verify that the content of the output file matches the input file
        assertEquals(testContent, outputContent)
    }
}
