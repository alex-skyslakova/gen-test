import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class RecursionLimitTest {

    @Test
    fun testRecursionLimit() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        val expectedOutputPattern = Regex("Limit of recursion is \\d+")

        main(arrayOf())

        val output = outContent.toString().trim()
        assert(expectedOutputPattern.matches(output)) { "Output did not match expected pattern. Output was: $output" }
    }
}
