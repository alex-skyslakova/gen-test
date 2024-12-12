import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.junit.jupiter.api.Assertions.*

class GeneralFizzbuzzTest {

    @Test
    fun testExampleCase() {
        val input = "20\n3 Fizz\n5 Buzz\n7 Baxx"
        val expectedOutput = """
            1
            2
            Fizz
            4
            Buzz
            Fizz
            Baxx
            8
            Fizz
            Buzz
            11
            Fizz
            13
            Baxx
            FizzBuzz
            16
            17
            Fizz
            19
            Buzz
            """.trimIndent()

        assertOutput(input, expectedOutput)
    }

    @Test
    fun testMax105() {
        val input = "105\n3 Fizz\n5 Buzz\n7 Baxx"
        val expectedOutput = generateExpectedOutput(105, mapOf(3 to "Fizz", 5 to "Buzz", 7 to "Baxx"))
       assertOutput(input, expectedOutput)

    }

    @Test
    fun testNoFactors() {
        val input = "10\n2 Foo\n4 Bar\n6 Baz"
        val expectedOutput = generateExpectedOutput(10, mapOf(2 to "Foo", 4 to "Bar", 6 to "Baz"))
        assertOutput(input, expectedOutput)

    }


    @Test
    fun testSingleFactor() {
        val input = "5\n2 Foo\n4 Bar\n5 Baz"
        val expectedOutput = generateExpectedOutput(5, mapOf(2 to "Foo", 4 to "Bar", 5 to "Baz"))
       assertOutput(input, expectedOutput)
    }


    @Test
    fun testZeroMax() {
        val input = "0\n3 Fizz\n5 Buzz\n7 Baxx"
        val expectedOutput = ""
        assertOutput(input, expectedOutput)

    }


    private fun assertOutput(input: String, expectedOutput: String) {
        val inputStream = ByteArrayInputStream(input.toByteArray())
        val outputStream = ByteArrayOutputStream()
        System.setIn(inputStream)
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        assertEquals(expectedOutput, outputStream.toString().trimIndent())
        System.setIn(System.`in`)
        System.setOut(System.out)

    }

        private fun generateExpectedOutput(max: Int, words: Map<Int, String>): String {
        val sortedWords = words.toSortedMap()
        val sb = StringBuilder()
        for (i in 1..max) {
            val wordsToPrint = sortedWords.filter { i % it.key == 0 }.map { it.value }
            if (wordsToPrint.isNotEmpty()) {
                sb.appendLine(wordsToPrint.joinToString(""))
            } else {
                sb.appendLine(i.toString())
            }
        }
        return sb.toString().trimIndent()
    }

}
