import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class LetterDeterminationTest {

    @Test
    fun testLetterOutput() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        main(arrayOf())

        val expectedOutput = "Lower case : abcdefghijklmnopqrstuvwxyz\nUpper case : ABCDEFGHIJKLMNOPQRSTUVWXYZ\n"
        assertEquals(expectedOutput, outContent.toString())
    }


}
