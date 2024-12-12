import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class HelloWorldNewlineOmissionTest {

    @Test
    fun testOutput() {
        val byteArrayOutputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(byteArrayOutputStream))

        main(arrayOf())

        val expectedOutput = "Goodbye, World!"
        val actualOutput = byteArrayOutputStream.toString()

        assert(actualOutput == expectedOutput) { "Expected '$expectedOutput', but got '$actualOutput'" }
    }
}
