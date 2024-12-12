import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class GeneralFizzbuzzTest {

    @Test
    fun testGeneralFizzbuzz() {
        val input = """
            20
            3 Fizz
            5 Buzz
            7 Baxx
        """.trimIndent()

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

        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        assertEquals(expectedOutput, outputStream.toString().trim())
    }

    @Test
    fun testGeneralFizzbuzzWithMaxZero() {
        val input = """
            0
            3 Fizz
            5 Buzz
            7 Baxx
        """.trimIndent()

        val expectedOutput = ""

        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        assertEquals(expectedOutput, outputStream.toString().trim())
    }

    @Test
    fun testGeneralFizzbuzzWithNoFactors() {
        val input = """
            10
            11 Empty
            13 Empty
            17 Empty
        """.trimIndent()

        val expectedOutput = """
            1
            2
            3
            4
            5
            6
            7
            8
            9
            10
        """.trimIndent()

        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        assertEquals(expectedOutput, outputStream.toString().trim())
    }

    @Test
    fun testGeneralFizzbuzzWithSingleFactor() {
        val input = """
            10
            2 Even
            4 Even
            6 Even
        """.trimIndent()

        val expectedOutput = """
            1
            Even
            3
            Even
            5
            Even
            7
            Even
            9
            Even
        """.trimIndent()

        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        assertEquals(expectedOutput, outputStream.toString().trim())
    }

    @Test
    fun testGeneralFizzbuzzWithAllFactors() {
        val input = """
            105
            3 Fizz
            5 Buzz
            7 Baxx
        """.trimIndent()

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
            FizzBaxx
            22
            23
            Fizz
            Buzz
            26
            Fizz
            Baxx
            29
            FizzBuzz
            31
            32
            Fizz
            34
            BuzzBaxx
            Fizz
            37
            38
            Fizz
            Buzz
            41
            FizzBaxx
            43
            44
            FizzBuzz
            46
            47
            Fizz
            Baxx
            Buzz
            Fizz
            52
            53
            Fizz
            Buzz
            Baxx
            Fizz
            58
            59
            FizzBuzz
            61
            62
            FizzBaxx
            64
            Buzz
            Fizz
            67
            68
            Fizz
            BuzzBaxx
            71
            Fizz
            73
            74
            FizzBuzz
            76
            Baxx
            Fizz
            79
            Buzz
            Fizz
            82
            83
            FizzBaxx
            Buzz
            86
            Fizz
            88
            89
            FizzBuzz
            Baxx
            92
            Fizz
            94
            Buzz
            Fizz
            97
            Baxx
            Fizz
            Buzz
            101
            Fizz
            103
            104
            FizzBuzzBaxx
        """.trimIndent()

        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        assertEquals(expectedOutput, outputStream.toString().trim())
    }
}
