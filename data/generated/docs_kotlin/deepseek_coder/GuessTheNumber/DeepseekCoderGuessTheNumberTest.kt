import org.junit.Test
import org.mockito.Mockito.*
import java.io.ByteArrayInputStream
import java.io.InputStream
import kotlin.test.assertEquals

class GuessTheNumberTest {

    @Test
    fun testCorrectGuess() {
        val input = "5\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val mockRandom = mock(java.util.Random::class.java)
        `when`(mockRandom.nextInt(10)).thenReturn(4) // 4 + 1 = 5

        GuessTheNumber.main(arrayOf())

        // Verify that the program prints "Well guessed!"
        val expectedOutput = "Guess which number I've chosen in the range 1 to 10\n\n Your guess : \nWell guessed!\n"
        assertEquals(expectedOutput, getOutput())
    }

    @Test
    fun testIncorrectGuessThenCorrectGuess() {
        val input = "3\n5\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val mockRandom = mock(java.util.Random::class.java)
        `when`(mockRandom.nextInt(10)).thenReturn(4) // 4 + 1 = 5

        GuessTheNumber.main(arrayOf())

        // Verify that the program prints "Well guessed!" after incorrect guess
        val expectedOutput = "Guess which number I've chosen in the range 1 to 10\n\n Your guess :  Your guess : \nWell guessed!\n"
        assertEquals(expectedOutput, getOutput())
    }

    @Test
    fun testMultipleIncorrectGuessesThenCorrectGuess() {
        val input = "1\n2\n3\n4\n5\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val mockRandom = mock(java.util.Random::class.java)
        `when`(mockRandom.nextInt(10)).thenReturn(4) // 4 + 1 = 5

        GuessTheNumber.main(arrayOf())

        // Verify that the program prints "Well guessed!" after multiple incorrect guesses
        val expectedOutput = "Guess which number I've chosen in the range 1 to 10\n\n Your guess :  Your guess :  Your guess :  Your guess :  Your guess : \nWell guessed!\n"
        assertEquals(expectedOutput, getOutput())
    }

    private fun getOutput(): String {
        val outputStream = System.out
        return outputStream.toString()
    }
}
