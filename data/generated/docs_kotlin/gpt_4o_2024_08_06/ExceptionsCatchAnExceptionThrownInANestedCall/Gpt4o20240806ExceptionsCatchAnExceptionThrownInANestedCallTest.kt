import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ExceptionTests {

    @Test
    fun testBazThrowsU0() {
        val exception = assertFailsWith<U0> {
            baz(1)
        }
        assertEquals("U0 occurred", exception.message)
    }

    @Test
    fun testBazThrowsU1() {
        val exception = assertFailsWith<U1> {
            baz(2)
        }
        assertEquals("U1 occurred", exception.message)
    }

    @Test
    fun testFooCatchesU0() {
        val output = captureOutput {
            foo()
        }
        assertTrue(output.contains("U0 occurred"))
    }

    @Test
    fun testFooDoesNotCatchU1() {
        assertFailsWith<U1> {
            foo()
        }
    }

    // Helper function to capture the output of a block of code
    private fun captureOutput(block: () -> Unit): String {
        val originalOut = System.out
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))
        try {
            block()
        } finally {
            System.setOut(originalOut)
        }
        return outputStream.toString().trim()
    }
}
