import org.junit.Test
import java.io.File
import java.util.*
import kotlin.test.assertEquals

class LongestStringChallengeTest {

    @Test
    fun testLongestStringChallenge() {
        // Prepare input file
        val inputContent = """
            a
            bb
            ccc
            ddd
            ee
            f
            ggg
        """.trimIndent()
        File("lines.txt").writeText(inputContent)

        // Run the main function
        main(arrayOf())

        // Capture the output
        val output = File("lines.txt.out").readText()

        // Expected output
        val expectedOutput = """
            ccc
            ddd
            ggg
        """.trimIndent()

        // Verify the output
        assertEquals(expectedOutput, output)
    }

    @Test
    fun testLongestStringChallengeWithEmptyInput() {
        // Prepare empty input file
        File("lines.txt").writeText("")

        // Run the main function
        main(arrayOf())

        // Capture the output
        val output = File("lines.txt.out").readText()

        // Expected output should be empty
        val expectedOutput = ""

        // Verify the output
        assertEquals(expectedOutput, output)
    }

    @Test
    fun testLongestStringChallengeWithSingleLine() {
        // Prepare input file with a single line
        File("lines.txt").writeText("single")

        // Run the main function
        main(arrayOf())

        // Capture the output
        val output = File("lines.txt.out").readText()

        // Expected output should be the single line
        val expectedOutput = "single"

        // Verify the output
        assertEquals(expectedOutput, output)
    }

    @Test
    fun testLongestStringChallengeWithAllSameLength() {
        // Prepare input file with lines of the same length
        val inputContent = """
            one
            two
            three
        """.trimIndent()
        File("lines.txt").writeText(inputContent)

        // Run the main function
        main(arrayOf())

        // Capture the output
        val output = File("lines.txt.out").readText()

        // Expected output should be all lines
        val expectedOutput = """
            one
            two
            three
        """.trimIndent()

        // Verify the output
        assertEquals(expectedOutput, output)
    }

    @Test
    fun testLongestStringChallengeWithMixedLengths() {
        // Prepare input file with mixed lengths
        val inputContent = """
            short
            longer
            longest
            medium
        """.trimIndent()
        File("lines.txt").writeText(inputContent)

        // Run the main function
        main(arrayOf())

        // Capture the output
        val output = File("lines.txt.out").readText()

        // Expected output should be the longest line
        val expectedOutput = "longest"

        // Verify the output
        assertEquals(expectedOutput, output)
    }
}
