import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LuckyNumbersTest {

    @Test
    fun testFilterLuckyOdd() {
        filterLuckyOdd()
        assertEquals(1, luckyOdd[0])
        assertEquals(3, luckyOdd[1])
        assertEquals(7, luckyOdd[2])
        assertEquals(9, luckyOdd[3])
    }

    @Test
    fun testFilterLuckyEven() {
        filterLuckyEven()
        assertEquals(2, luckyEven[0])
        assertEquals(4, luckyEven[1])
        assertEquals(6, luckyEven[2])
        assertEquals(10, luckyEven[3])
    }

    @Test
    fun testPrintSingleLucky() {
        filterLuckyOdd()
        val output = captureOutput {
            printSingle(1, true)
        }
        assertEquals("Lucky number 1 = 1\n", output)
    }

    @Test
    fun testPrintSingleEvenLucky() {
        filterLuckyEven()
        val output = captureOutput {
            printSingle(1, false)
        }
        assertEquals("Lucky even number 1 = 2\n", output)
    }

    @Test
    fun testPrintRangeLucky() {
        filterLuckyOdd()
        val output = captureOutput {
            printRange(1, 3, true)
        }
        assertEquals("Lucky numbers 1 to 3 are:\n[1, 3, 7]\n", output)
    }

    @Test
    fun testPrintRangeEvenLucky() {
        filterLuckyEven()
        val output = captureOutput {
            printRange(1, 3, false)
        }
        assertEquals("Lucky even numbers 1 to 3 are:\n[2, 4, 6]\n", output)
    }

    @Test
    fun testPrintBetweenLucky() {
        filterLuckyOdd()
        val output = captureOutput {
            printBetween(7, 25, true)
        }
        assertEquals("Lucky numbers between 7 and 25 are:\n[7, 9, 13, 15, 21, 25]\n", output)
    }

    @Test
    fun testPrintBetweenEvenLucky() {
        filterLuckyEven()
        val output = captureOutput {
            printBetween(6, 20, false)
        }
        assertEquals("Lucky even numbers between 6 and 20 are:\n[6, 10, 12, 18, 20]\n", output)
    }

    @Test
    fun testMainWithSingleArgument() {
        val output = captureOutput {
            main(arrayOf("1"))
        }
        assertEquals("Lucky number 1 = 1\n", output)
    }

    @Test
    fun testMainWithTwoArguments() {
        val output = captureOutput {
            main(arrayOf("1", "3"))
        }
        assertEquals("Lucky numbers 1 to 3 are:\n[1, 3, 7]\n", output)
    }

    @Test
    fun testMainWithThreeArgumentsLucky() {
        val output = captureOutput {
            main(arrayOf("1", "3", "lucky"))
        }
        assertEquals("Lucky numbers 1 to 3 are:\n[1, 3, 7]\n", output)
    }

    @Test
    fun testMainWithThreeArgumentsEvenLucky() {
        val output = captureOutput {
            main(arrayOf("1", "3", "evenLucky"))
        }
        assertEquals("Lucky even numbers 1 to 3 are:\n[2, 4, 6]\n", output)
    }

    @Test
    fun testMainWithInvalidArguments() {
        assertThrows<IllegalArgumentException> {
            main(arrayOf("1", "3", "invalid"))
        }
    }

    @Test
    fun testMainWithMissingArguments() {
        assertThrows<IllegalArgumentException> {
            main(arrayOf())
        }
    }

    @Test
    fun testMainWithTooManyArguments() {
        assertThrows<IllegalArgumentException> {
            main(arrayOf("1", "3", "lucky", "extra"))
        }
    }

    private fun captureOutput(block: () -> Unit): String {
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))
        block()
        return outputStream.toString()
    }
}
