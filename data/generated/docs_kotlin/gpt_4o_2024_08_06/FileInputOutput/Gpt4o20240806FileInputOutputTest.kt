import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals

class FileInputOutputTest {

    private val inputFileName = "input.txt"
    private val outputFileName = "output.txt"

    @BeforeEach
    fun setUp() {
        // Create a temporary input file with some content
        File(inputFileName).writeText("This is a test content.")
    }

    @AfterEach
    fun tearDown() {
        // Clean up the files after each test
        File(inputFileName).delete()
        File(outputFileName).delete()
    }

    @Test
    fun testFileCopy() {
        // Run the main function to copy content from input.txt to output.txt
        main(arrayOf())

        // Read the contents of both files
        val inputContent = File(inputFileName).readText()
        val outputContent = File(outputFileName).readText()

        // Assert that the contents are the same
        assertEquals(inputContent, outputContent, "The content of the output file should match the input file.")
    }

    @Test
    fun testEmptyInputFile() {
        // Create an empty input file
        File(inputFileName).writeText("")

        // Run the main function to copy content from input.txt to output.txt
        main(arrayOf())

        // Read the contents of the output file
        val outputContent = File(outputFileName).readText()

        // Assert that the output file is also empty
        assertEquals("", outputContent, "The output file should be empty when the input file is empty.")
    }

    @Test
    fun testNonExistentInputFile() {
        // Delete the input file to simulate a non-existent file scenario
        File(inputFileName).delete()

        // Expect an exception when trying to read a non-existent file
        val exception = assertThrows<Exception> {
            main(arrayOf())
        }

        // Assert that the exception message is as expected
        assertEquals("input.txt (No such file or directory)", exception.message)
    }
}
