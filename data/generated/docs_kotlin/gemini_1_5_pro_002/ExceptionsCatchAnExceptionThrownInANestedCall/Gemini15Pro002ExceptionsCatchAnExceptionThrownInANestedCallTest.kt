import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayOutputStream
import java.io.PrintStream


class ExceptionTests {

    @Test
    fun testFooCatchesU0() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        foo()

        val expectedOutput = "U0 occurred\n"
        val actualOutput = outputStream.toString()
       org.junit.jupiter.api.Assertions.assertEquals(expectedOutput, actualOutput)
        
    }

    @Test
    fun testBarThrowsU1() {
         assertThrows<U1> { bar(2) }
    }

    @Test
    fun testBazThrowsU0() {
        assertThrows<U0> { baz(1) }
    }

    @Test
    fun testBazThrowsU1() {
        assertThrows<U1> { baz(2) }
    }

}




