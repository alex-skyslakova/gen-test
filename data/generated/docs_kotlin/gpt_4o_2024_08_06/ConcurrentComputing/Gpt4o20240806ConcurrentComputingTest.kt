import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals
import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import java.util.regex.Pattern

class ConcurrentComputingTest {

    @Test
    fun testConcurrentPrinting() {
        val expectedMessages = setOf("Enjoy", "Rosetta", "Code")
        
        val output = tapSystemOut {
            main(arrayOf())
        }

        // Split the output into lines and trim each line
        val lines = output.lines().map { it.trim() }.filter { it.isNotEmpty() }

        // Check that we have exactly three lines
        assertEquals(3, lines.size, "Output should contain exactly three lines.")

        // Check that all expected messages are present
        val outputMessages = lines.toSet()
        assertTrue(outputMessages.containsAll(expectedMessages), "Output should contain all expected messages.")

        // Check that there are no duplicate messages
        assertEquals(expectedMessages.size, outputMessages.size, "Output should not contain duplicate messages.")
    }
}
