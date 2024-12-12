import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals

class LongestStringChallengeTest {

    private val testFileName = "lines.txt"

    @BeforeEach
    fun setup() {
        // Ensure the test file is clean before each test
        File(testFileName).delete()
    }

    @AfterEach
    fun teardown() {
        // Clean up the test file after each test
        File(testFileName).delete()
    }

    @Test
    fun testSingleLongestLine() {
        File(testFileName).writeText("a\nbb\nccc\nddd\nee\nf\nggg")
        val expectedOutput = "ccc\nddd\nggg\n"
        assertEquals(expectedOutput, captureOutput { main(arrayOf()) })
    }

    @Test
    fun testMultipleLongestLines() {
        File(testFileName).writeText("a\nbb\nccc\nddd\nee\nf\nggg\nhhh")
        val expectedOutput = "ccc\nddd\nggg\nhhh\n"
        assertEquals(expectedOutput, captureOutput { main(arrayOf()) })
    }

    @Test
    fun testNoInput() {
        File(testFileName).writeText("")
        val expectedOutput = "\n"
        assertEquals(expectedOutput, captureOutput { main(arrayOf()) })
    }

    @Test
    fun testAllLinesSameLength() {
        File(testFileName).writeText("aa\nbb\ncc\ndd\nee")
        val expectedOutput = "aa\nbb\ncc\ndd\nee\n"
        assertEquals(expectedOutput, captureOutput { main(arrayOf()) })
    }

    @Test
    fun testSingleLine() {
        File(testFileName).writeText("singleline")
        val expectedOutput = "singleline\n"
        assertEquals(expectedOutput, captureOutput { main(arrayOf()) })
    }

    private fun captureOutput(block: () -> Unit): String {
        val originalOut = System.out
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))
        try {
            block()
        } finally {
            System.setOut(originalOut)
        }
        return outputStream.toString()
    }
}
