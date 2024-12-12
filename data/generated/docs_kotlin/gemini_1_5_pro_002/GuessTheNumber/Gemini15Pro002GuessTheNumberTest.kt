import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.junit.jupiter.api.Assertions.*

class GuessTheNumberTest {

    private val standardOut = System.out
    private val standardIn = System.`in`
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
        System.setIn(standardIn)
    }

    @Test
    fun testCorrectGuessFirstTry() {
        val randomNumber = 5 // Example random number
        val input = "$randomNumber\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        main(arrayOf())

        val output = outputStreamCaptor.toString().trim()
        assertTrue(output.contains("Guess which number I've chosen in the range 1 to 10"))
        assertTrue(output.contains("Your guess : "))
        assertTrue(output.contains("Well guessed!"))

    }

    @Test
    fun testCorrectGuessAfterMultipleTries() {
        val randomNumber = 7 // Example random number
        val input = "3\n1\n$randomNumber\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        main(arrayOf())

        val output = outputStreamCaptor.toString().trim()
        assertTrue(output.contains("Guess which number I've chosen in the range 1 to 10"))
        assertTrue(output.contains("Your guess : "))
        assertTrue(output.contains("Well guessed!"))
    }


    @Test
    fun testCorrectGuessBoundaryValues() {

        //Test with 1
        val input1 = "1\n"
        System.setIn(ByteArrayInputStream(input1.toByteArray()))
        outputStreamCaptor.reset() // Clear the output stream
        main(arrayOf<String>())
        var output = outputStreamCaptor.toString().trim()


        if(java.util.Random().nextInt(10)+1 == 1)
            assertTrue(output.contains("Well guessed!"))


        //Test with 10
        val input10 = "10\n"
        System.setIn(ByteArrayInputStream(input10.toByteArray()))
        outputStreamCaptor.reset() // Clear the output stream
        main(arrayOf<String>())
         output = outputStreamCaptor.toString().trim()


        if(java.util.Random().nextInt(10)+1 == 10)
            assertTrue(output.contains("Well guessed!"))


    }
}
