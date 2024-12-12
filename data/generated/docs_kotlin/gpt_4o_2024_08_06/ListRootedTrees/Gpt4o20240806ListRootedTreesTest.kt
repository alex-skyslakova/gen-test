import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ListRootedTreesTest {

    @Test
    fun testMainWithValidInput() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf("3"))

        val expectedOutput = """
            Number of 3-trees: 2
            ((()))
            (()())
            
        """.trimIndent()

        assertEquals(expectedOutput, outputStream.toString().trim())
    }

    @Test
    fun testMainWithInvalidInputNonNumeric() {
        val exception = assertThrows<IllegalArgumentException> {
            main(arrayOf("abc"))
        }
        assertEquals("Argument is not a valid number", exception.message)
    }

    @Test
    fun testMainWithInvalidInputOutOfRangeLow() {
        val exception = assertThrows<IllegalArgumentException> {
            main(arrayOf("0"))
        }
        assertEquals("Argument must be between 1 and 12", exception.message)
    }

    @Test
    fun testMainWithInvalidInputOutOfRangeHigh() {
        val exception = assertThrows<IllegalArgumentException> {
            main(arrayOf("13"))
        }
        assertEquals("Argument must be between 1 and 12", exception.message)
    }

    @Test
    fun testMainWithNoArguments() {
        val exception = assertThrows<IllegalArgumentException> {
            main(arrayOf())
        }
        assertEquals("There must be exactly 1 command line argument", exception.message)
    }

    @Test
    fun testMainWithMultipleArguments() {
        val exception = assertThrows<IllegalArgumentException> {
            main(arrayOf("3", "4"))
        }
        assertEquals("There must be exactly 1 command line argument", exception.message)
    }
}
