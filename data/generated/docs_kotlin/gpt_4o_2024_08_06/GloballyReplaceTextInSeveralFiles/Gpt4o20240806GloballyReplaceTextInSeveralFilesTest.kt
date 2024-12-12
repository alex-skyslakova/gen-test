import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals

class GloballyReplaceTextInSeveralFilesTest {

    private val file1 = File("file1.txt")
    private val file2 = File("file2.txt")

    @BeforeEach
    fun setUp() {
        // Set up initial content for the test files
        file1.writeText("This is a test. Goodbye London! See you soon.")
        file2.writeText("Goodbye London! We will miss you. Goodbye London!")
    }

    @AfterEach
    fun tearDown() {
        // Clean up the test files after each test
        file1.delete()
        file2.delete()
    }

    @Test
    fun `test text replacement in file1`() {
        main(arrayOf())

        val expectedContent = "This is a test. Hello New York! See you soon."
        val actualContent = file1.readText()

        assertEquals(expectedContent, actualContent, "The content of file1.txt should be correctly replaced.")
    }

    @Test
    fun `test text replacement in file2`() {
        main(arrayOf())

        val expectedContent = "Hello New York! We will miss you. Hello New York!"
        val actualContent = file2.readText()

        assertEquals(expectedContent, actualContent, "The content of file2.txt should be correctly replaced.")
    }

    @Test
    fun `test no replacement needed`() {
        file1.writeText("This is a test. No replacement needed here.")
        file2.writeText("Another line without the target text.")

        main(arrayOf())

        val expectedContentFile1 = "This is a test. No replacement needed here."
        val expectedContentFile2 = "Another line without the target text."

        assertEquals(expectedContentFile1, file1.readText(), "The content of file1.txt should remain unchanged.")
        assertEquals(expectedContentFile2, file2.readText(), "The content of file2.txt should remain unchanged.")
    }
}
