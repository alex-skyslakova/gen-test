import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class GuessTheNumberTest {

    private val originalOut = System.out
    private val originalIn = System.`in`
    private lateinit var outputStream: ByteArrayOutputStream

    @BeforeEach
    fun setUp() {
        outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(originalOut)
        System.setIn(originalIn)
    }

    @Test
    fun testCorrectGuessOnFirstTry() {
        val input = "5\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // Mock the random number to be 5
        val randomNumber = 5
        val guessTheNumber = GuessTheNumber(randomNumber)
        guessTheNumber.startGame()

        val expectedOutput = "Guess which number I've chosen in the range 1 to 10\n Your guess : \nWell guessed!\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testCorrectGuessAfterSeveralTries() {
        val input = "3\n7\n5\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // Mock the random number to be 5
        val randomNumber = 5
        val guessTheNumber = GuessTheNumber(randomNumber)
        guessTheNumber.startGame()

        val expectedOutput = "Guess which number I've chosen in the range 1 to 10\n Your guess :  Your guess :  Your guess : \nWell guessed!\n"
        assertEquals(expectedOutput, outputStream.toString())
    }
}

class GuessTheNumber(private val randomNumber: Int) {
    fun startGame() {
        println("Guess which number I've chosen in the range 1 to 10\n")
        do {
            print(" Your guess : ")
        } while (randomNumber.toString() != readLine())
        println("\nWell guessed!")
    }
}
