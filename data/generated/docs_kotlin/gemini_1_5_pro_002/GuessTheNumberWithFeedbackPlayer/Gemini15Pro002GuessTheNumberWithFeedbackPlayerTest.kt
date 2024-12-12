import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.junit.jupiter.api.Assertions.*

class GuessTheNumberWithFeedbackPlayerTest {

    private fun runGame(input: String): String {
        val inputStream = ByteArrayInputStream(input.toByteArray())
        val outputStream = ByteArrayOutputStream()
        System.setIn(inputStream)
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        return outputStream.toString()
    }

    @Test
    fun testCorrectGuessFirstTry() {
        val output = runGame("e\n")
        assertTrue(output.contains("My guess is 10"))
        assertTrue(output.contains("Good, thanks for playing the game with me!"))
    }

    @Test
    fun testCorrectGuessAfterFewTries() {
        val output = runGame("l\nh\ne\n")
        assertTrue(output.contains("My guess is 10"))
        assertTrue(output.contains("My guess is 15"))
        assertTrue(output.contains("My guess is 12"))
        assertTrue(output.contains("Good, thanks for playing the game with me!"))

    }


    @Test
    fun testLowerBoundary() {
        val output = runGame("h\nh\nh\nh\nh\nh\ne\n") // Number is 1
        assertTrue(output.contains("My guess is 1"))
        assertTrue(output.contains("Good, thanks for playing the game with me!"))
    }

    @Test
    fun testUpperBoundary() {
        val output = runGame("l\nl\nl\nl\nl\ne\n") // Number is 20
        assertTrue(output.contains("My guess is 20"))
        assertTrue(output.contains("Good, thanks for playing the game with me!"))
    }

    @Test
    fun testInvalidInputHandlingHigher() {
        val output = runGame("l\nl\nl\nl\nl\nh\ne\n") // Tries to go higher than possible
        assertTrue(output.contains("It can't be more than"))
        assertTrue(output.contains("Good, thanks for playing the game with me!"))
    }


    @Test
    fun testInvalidInputHandlingLower() {
        val output = runGame("h\nh\nh\nh\nh\nl\ne\n") // Tries to go lower than possible
        assertTrue(output.contains("It can't be less than"))
        assertTrue(output.contains("Good, thanks for playing the game with me!"))

    }



}
