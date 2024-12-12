import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LetterFrequencyTest {

    @Test
    fun testLetterFrequency() {
        // Create a temporary file with test content
        val tempFile = File.createTempFile("test", ".txt")
        tempFile.writeText("Hello, World! This is a test.")

        // Read the file and count the letter frequencies
        val text = File(tempFile.absolutePath).readText().toLowerCase()
        val letterMap = text.filter { it in 'a'..'z' }.groupBy { it }.toSortedMap()

        // Expected letter frequencies
        val expectedMap = mapOf(
            'a' to 1,
            'd' to 1,
            'e' to 2,
            'h' to 1,
            'i' to 2,
            'l' to 3,
            'o' to 3,
            'r' to 1,
            's' to 2,
            't' to 3,
            'w' to 1
        )

        // Verify the letter frequencies
        assertEquals(expectedMap, letterMap)

        // Verify the total number of letters
        val sum = letterMap.values.sumBy { it.size }
        assertEquals(20, sum)

        // Clean up the temporary file
        tempFile.delete()
    }

    @Test
    fun testEmptyFile() {
        // Create an empty temporary file
        val tempFile = File.createTempFile("empty", ".txt")

        // Read the file and count the letter frequencies
        val text = File(tempFile.absolutePath).readText().toLowerCase()
        val letterMap = text.filter { it in 'a'..'z' }.groupBy { it }.toSortedMap()

        // Verify that the letter map is empty
        assertTrue(letterMap.isEmpty())

        // Verify the total number of letters
        val sum = letterMap.values.sumBy { it.size }
        assertEquals(0, sum)

        // Clean up the temporary file
        tempFile.delete()
    }

    @Test
    fun testNonAlphabetCharacters() {
        // Create a temporary file with non-alphabet characters
        val tempFile = File.createTempFile("non_alphabet", ".txt")
        tempFile.writeText("12345!@#$%^&*()_+[]{};':\",./<>?")

        // Read the file and count the letter frequencies
        val text = File(tempFile.absolutePath).readText().toLowerCase()
        val letterMap = text.filter { it in 'a'..'z' }.groupBy { it }.toSortedMap()

        // Verify that the letter map is empty
        assertTrue(letterMap.isEmpty())

        // Verify the total number of letters
        val sum = letterMap.values.sumBy { it.size }
        assertEquals(0, sum)

        // Clean up the temporary file
        tempFile.delete()
    }

    @Test
    fun testMixedCharacters() {
        // Create a temporary file with mixed characters
        val tempFile = File.createTempFile("mixed", ".txt")
        tempFile.writeText("Hello123!@#$%^&*()_+[]{};':\",./<>?")

        // Read the file and count the letter frequencies
        val text = File(tempFile.absolutePath).readText().toLowerCase()
        val letterMap = text.filter { it in 'a'..'z' }.groupBy { it }.toSortedMap()

        // Expected letter frequencies
        val expectedMap = mapOf(
            'e' to 1,
            'h' to 1,
            'l' to 2,
            'o' to 1
        )

        // Verify the letter frequencies
        assertEquals(expectedMap, letterMap)

        // Verify the total number of letters
        val sum = letterMap.values.sumBy { it.size }
        assertEquals(5, sum)

        // Clean up the temporary file
        tempFile.delete()
    }
}
