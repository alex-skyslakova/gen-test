import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class GuessTheNumberWithFeedbackPlayerTest {

    @Test
    fun testCorrectGuessOnFirstTry() {
        val input = "e\n"
        val expectedOutput = "Please choose a number between 1 and 20 but don't tell me what it is yet\n" +
                "My guess is 10\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "Good, thanks for playing the game with me!\n"

        assertGameOutput(input, expectedOutput)
    }

    @Test
    fun testGuessLowerThenCorrect() {
        val input = "l\ne\n"
        val expectedOutput = "Please choose a number between 1 and 20 but don't tell me what it is yet\n" +
                "My guess is 10\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "My guess is 15\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "Good, thanks for playing the game with me!\n"

        assertGameOutput(input, expectedOutput)
    }

    @Test
    fun testGuessHigherThenCorrect() {
        val input = "h\ne\n"
        val expectedOutput = "Please choose a number between 1 and 20 but don't tell me what it is yet\n" +
                "My guess is 10\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "My guess is 5\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "Good, thanks for playing the game with me!\n"

        assertGameOutput(input, expectedOutput)
    }

    @Test
    fun testInvalidInputHandling() {
        val input = "x\nl\ne\n"
        val expectedOutput = "Please choose a number between 1 and 20 but don't tell me what it is yet\n" +
                "My guess is 10\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "My guess is 15\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "Good, thanks for playing the game with me!\n"

        assertGameOutput(input, expectedOutput)
    }

    @Test
    fun testBoundaryConditionHigher() {
        val input = "h\nh\nh\nh\ne\n"
        val expectedOutput = "Please choose a number between 1 and 20 but don't tell me what it is yet\n" +
                "My guess is 10\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "My guess is 5\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "My guess is 3\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "My guess is 2\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "My guess is 1\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "Good, thanks for playing the game with me!\n"

        assertGameOutput(input, expectedOutput)
    }

    @Test
    fun testBoundaryConditionLower() {
        val input = "l\nl\nl\nl\ne\n"
        val expectedOutput = "Please choose a number between 1 and 20 but don't tell me what it is yet\n" +
                "My guess is 10\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "My guess is 15\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "My guess is 18\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "My guess is 19\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "My guess is 20\n" +
                "Is this higher/lower than or equal to your chosen number h/l/e : " +
                "Good, thanks for playing the game with me!\n"

        assertGameOutput(input, expectedOutput)
    }

    private fun assertGameOutput(input: String, expectedOutput: String) {
        val inputStream = ByteArrayInputStream(input.toByteArray())
        val outputStream = ByteArrayOutputStream()
        System.setIn(inputStream)
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        assertEquals(expectedOutput, outputStream.toString())
    }
}
