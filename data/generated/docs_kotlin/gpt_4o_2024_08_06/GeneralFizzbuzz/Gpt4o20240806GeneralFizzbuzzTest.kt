import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class GeneralFizzBuzzTest {

    private fun runTest(input: String, expectedOutput: String) {
        val inputStream = ByteArrayInputStream(input.toByteArray())
        val outputStream = ByteArrayOutputStream()
        System.setIn(inputStream)
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        assertEquals(expectedOutput.trim(), outputStream.toString().trim())
    }

    @Test
    fun testExampleCase() {
        val input = "20\n3 Fizz\n5 Buzz\n7 Baxx\n"
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
        runTest(input, expectedOutput)
    }

    @Test
    fun testNoFactors() {
        val input = "5\n1 One\n2 Two\n3 Three\n"
        val expectedOutput = """
            One
            OneTwo
            OneThree
            OneTwo
            One
        """.trimIndent()
        runTest(input, expectedOutput)
    }

    @Test
    fun testSingleFactor() {
        val input = "10\n2 Even\n4 Four\n8 Eight\n"
        val expectedOutput = """
            1
            Even
            3
            EvenFour
            5
            Even
            7
            EvenFourEight
            9
            Even
        """.trimIndent()
        runTest(input, expectedOutput)
    }

    @Test
    fun testMultipleFactors() {
        val input = "15\n3 Fizz\n5 Buzz\n15 FizzBuzz\n"
        val expectedOutput = """
            1
            2
            Fizz
            4
            Buzz
            Fizz
            7
            8
            Fizz
            Buzz
            11
            Fizz
            13
            14
            FizzBuzz
        """.trimIndent()
        runTest(input, expectedOutput)
    }

    @Test
    fun testMaxNumberZero() {
        val input = "0\n3 Fizz\n5 Buzz\n7 Baxx\n"
        val expectedOutput = ""
        runTest(input, expectedOutput)
    }
}
