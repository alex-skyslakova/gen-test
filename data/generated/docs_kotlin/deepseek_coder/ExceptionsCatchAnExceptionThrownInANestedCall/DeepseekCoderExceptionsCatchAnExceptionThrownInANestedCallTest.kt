import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ExceptionsCatchAnExceptionThrownInANestedCallTest {

    @Test
    fun testFooCatchesU0() {
        // Capture the standard output to check the printed message
        val outputStream = System.out
        val printStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(printStream))

        try {
            foo()
            val output = printStream.toString().trim()
            assertEquals("U0 occurred", output)
        } finally {
            // Reset the standard output
            System.setOut(outputStream)
        }
    }

    @Test
    fun testFooDoesNotCatchU1() {
        // Ensure that U1 is not caught by foo and propagates
        assertFailsWith<U1> {
            foo()
        }
    }

    @Test
    fun testBarCallsBaz() {
        // Ensure that bar calls baz
        assertFailsWith<U0> {
            bar(1)
        }
        assertFailsWith<U1> {
            bar(2)
        }
    }

    @Test
    fun testBazThrowsU0OnFirstCall() {
        // Ensure that baz throws U0 on the first call
        assertFailsWith<U0> {
            baz(1)
        }
    }

    @Test
    fun testBazThrowsU1OnSecondCall() {
        // Ensure that baz throws U1 on the second call
        assertFailsWith<U1> {
            baz(2)
        }
    }
}
