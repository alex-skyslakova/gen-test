import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import java.io.File
import kotlin.test.assertEquals

class GloballyReplaceTextInSeveralFilesTest {

    private val testFiles = arrayOf("testFile1.txt", "testFile2.txt")

    @BeforeEach
    fun setUp() {
        // Create test files with initial content
        for (file in testFiles) {
            File(file).writeText("Goodbye London!")
        }
    }

    @AfterEach
    fun tearDown() {
        // Clean up test files after each test
        for (file in testFiles) {
            File(file).delete()
        }
    }

    @Test
    fun testTextReplacement() {
        // Run the main function to replace text in test files
        main(arrayOf())

        // Verify that the text has been replaced correctly
        for (file in testFiles) {
            val content = File(file).readText()
            assertEquals("Hello New York!", content)
        }
    }

    @Test
    fun testNoReplacementWhenTextDoesNotExist() {
        // Create test files with content that should not be replaced
        for (file in testFiles) {
            File(file).writeText("Some other text")
        }

        // Run the main function to replace text in test files
        main(arrayOf())

        // Verify that the text has not been replaced
        for (file in testFiles) {
            val content = File(file).readText()
            assertEquals("Some other text", content)
        }
    }

    @Test
    fun testEmptyFile() {
        // Create test files with empty content
        for (file in testFiles) {
            File(file).writeText("")
        }

        // Run the main function to replace text in test files
        main(arrayOf())

        // Verify that the files remain empty
        for (file in testFiles) {
            val content = File(file).readText()
            assertEquals("", content)
        }
    }
}
