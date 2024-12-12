import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.junit.jupiter.api.Assertions.*

class HelloWorldStandardErrorTest {

    @Test
    fun testStandardErrorOutput() {
        val originalErr = System.err
        val baos = ByteArrayOutputStream()
        val newErr = PrintStream(baos)
        System.setErr(newErr)

        main(arrayOf())

        System.setErr(originalErr) 

        val expectedOutput = "Goodbye, World!\n"
        assertEquals(expectedOutput, baos.toString())
    }
}
