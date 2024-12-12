import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.assertEquals

class LetterFrequencyTest {

    @TempDir
    lateinit var tempDir: File

    @Test
    fun testEmptyFile() {
        val inputFile = File(tempDir, "input.txt")
        inputFile.createNewFile()

        val expectedOutput = ""
        val actualOutput = executeLetterFrequency(inputFile)

        assertEquals(expectedOutput, actualOutput.trim())
    }


    @Test
    fun testOnlyLetters() {
        val inputFile = File(tempDir, "input.txt")
        inputFile.writeText("abcabcabc")

        val expectedOutput = """
            a = 3
            b = 3
            c = 3
            
            Total letters = 9
        """.trimIndent()
        val actualOutput = executeLetterFrequency(inputFile)

        assertEquals(expectedOutput, actualOutput.trim())

    }

    @Test
    fun testMixedCaseAndPunctuation() {
        val inputFile = File(tempDir, "input.txt")
        inputFile.writeText("Hello, World! This is a test.")

        val expectedOutput = """
            a = 1
            d = 1
            e = 1
            h = 1
            i = 2
            l = 2
            o = 1
            r = 1
            s = 3
            t = 3
            w = 1
            
            Total letters = 17
        """.trimIndent()

        val actualOutput = executeLetterFrequency(inputFile)

        assertEquals(expectedOutput, actualOutput.trim())
    }

    @Test
    fun testNumbersAndSpecialChars() {
        val inputFile = File(tempDir, "input.txt")
        inputFile.writeText("12345!@#$%^")


        val expectedOutput = ""

        val actualOutput = executeLetterFrequency(inputFile)

        assertEquals(expectedOutput, actualOutput.trim())
    }



    private fun executeLetterFrequency(inputFile: File): String {
        val output = StringBuilder()
        val text = inputFile.readText().toLowerCase()
        val letterMap = text.filter { it in 'a'..'z' }.groupBy { it }.toSortedMap()
        for (letter in letterMap) output.appendLine("${letter.key} = ${letter.value.size}")
        val sum = letterMap.values.sumBy { it.size }
        output.appendLine("\nTotal letters = $sum")

        return output.toString()
    }
}

