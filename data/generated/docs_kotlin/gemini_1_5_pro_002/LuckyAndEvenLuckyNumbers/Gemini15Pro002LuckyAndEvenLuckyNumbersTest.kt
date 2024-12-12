import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class LuckyNumberTest {

    private val originalOut = System.out
    private val outputStreamCaptor = ByteArrayOutputStream()

    @Test
    fun testNoArguments() {
        assertThrows<IllegalArgumentException> { main(arrayOf()) }
    }

    @Test
    fun testTooManyArguments() {
        assertThrows<IllegalArgumentException> { main(arrayOf("1", "2", "lucky", "extra")) }
    }

    @Test
    fun testInvalidFirstArgument() {
        assertThrows<IllegalArgumentException> { main(arrayOf("a")) }
        assertThrows<IllegalArgumentException> { main(arrayOf("0")) }
        assertThrows<IllegalArgumentException> { main(arrayOf("-1")) }
    }

    @Test
    fun testInvalidSecondArgument() {
        assertThrows<IllegalArgumentException> { main(arrayOf("1", "a")) }
    }

    @Test
    fun testInvalidThirdArgument() {
        assertThrows<IllegalArgumentException> { main(arrayOf("1", "2", "invalid")) }
    }

    @Test
    fun testSecondArgumentLessThanFirst() {
        assertThrows<IllegalArgumentException> { main(arrayOf("2", "1")) }
        assertThrows<IllegalArgumentException> { main(arrayOf("2", "1", "lucky")) }
        assertThrows<IllegalArgumentException> { main(arrayOf("2", "-1"))}
        assertThrows<IllegalArgumentException> { main(arrayOf("2", "-1", "lucky"))}

    }

    @Test
    fun testSingleLucky() {
        captureOutput()
        main(arrayOf("1"))
        assertEquals("Lucky number 1 = 1\n", outputStreamCaptor.toString())

        captureOutput()
        main(arrayOf("2"))
        assertEquals("Lucky number 2 = 3\n", outputStreamCaptor.toString())

        captureOutput()
        main(arrayOf("10000")) // Test a large index
        assertEquals("Lucky number 10000 = 21397\n", outputStreamCaptor.toString())
    }



    @Test
    fun testSingleEvenLucky() {
        captureOutput()
        main(arrayOf("1", ",", "evenLucky"))
        assertEquals("Lucky even number 1 = 2\n", outputStreamCaptor.toString())


        captureOutput()
        main(arrayOf("2", ",", "evenLucky"))
        assertEquals("Lucky even number 2 = 4\n", outputStreamCaptor.toString())


         captureOutput()
        main(arrayOf("10000", ",", "EvenLucky"))
        assertEquals("Lucky even number 10000 = 21446\n", outputStreamCaptor.toString())

    }

    @Test
    fun testRangeLucky() {
         captureOutput()
        main(arrayOf("1", "3"))
        assertEquals("Lucky numbers 1 to 3 are:\n[1, 3, 7]\n", outputStreamCaptor.toString())


          captureOutput()
        main(arrayOf("1", "3", "Lucky"))
        assertEquals("Lucky numbers 1 to 3 are:\n[1, 3, 7]\n", outputStreamCaptor.toString())

    }



    @Test
    fun testRangeEvenLucky() {
        captureOutput()
        main(arrayOf("1", "3", "evenLucky"))
        assertEquals("Lucky even numbers 1 to 3 are:\n[2, 4, 6]\n", outputStreamCaptor.toString())
    }


    @Test
    fun testBetweenLucky() {
        captureOutput()
        main(arrayOf("10", "-20"))
        assertEquals("Lucky numbers between 10 and 20 are:\n[13, 15, 19]\n", outputStreamCaptor.toString())

          captureOutput()
        main(arrayOf("10", "-20", "lucky"))
        assertEquals("Lucky numbers between 10 and 20 are:\n[13, 15, 19]\n", outputStreamCaptor.toString())


    }




    @Test
    fun testBetweenEvenLucky() {

        captureOutput()
        main(arrayOf("10", "-20", "evenLucky"))
        assertEquals("Lucky even numbers between 10 and 20 are:\n[12, 14, 18, 20]\n", outputStreamCaptor.toString())
    }




     @Test
    fun testMixedCase() {
        captureOutput()
        main(arrayOf("1", ",", "eVenLUcKY"))
        assertEquals("Lucky even number 1 = 2\n", outputStreamCaptor.toString())
    }


    private fun captureOutput() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    // Restores System.out for other tests
     //@AfterEach 
    fun restoreOutput() {
        System.setOut(originalOut)
    }

}
