import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.*

class EvolutionaryAlgorithmTest {

    @Test
    fun testRandomChar() {
        val char = randomChar()
        assertTrue(validChars.contains(char))
    }

    @Test
    fun testHammingDistance() {
        val s1 = "METHINKS IT IS LIKE A WEASEL"
        val s2 = "METHINKS IT IS LIKE A WEASEL"
        val s3 = "METHINKS IT IS LIKE A WEASEL"
        val s4 = "METHINKS IT IS LIKE A WEASEL"

        assertEquals(0, hammingDistance(s1, s2))
        assertEquals(0, hammingDistance(s3, s4))
    }

    @Test
    fun testFitness() {
        val perfectMatch = "METHINKS IT IS LIKE A WEASEL"
        val noMatch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val partialMatch = "METHINKS IT IS LIKE A WEASEL"

        assertEquals(target.length, fitness(perfectMatch))
        assertEquals(0, fitness(noMatch))
        assertTrue(fitness(partialMatch) > 0)
    }

    @Test
    fun testMutate() {
        val initialString = "METHINKS IT IS LIKE A WEASEL"
        val mutationRate = 0.05
        val mutatedString = mutate(initialString, mutationRate)

        // Check that the mutated string is not exactly the same as the initial string
        assertNotEquals(initialString, mutatedString)

        // Check that the mutated string contains only valid characters
        assertTrue(mutatedString.all { validChars.contains(it) })
    }

    @Test
    fun testMain() {
        val initialString = (0 until target.length).map { randomChar() }.joinToString(separator = "")
        val mutationRate = 0.05
        val childrenPerGen = 50

        var i = 0
        var currVal = initialString
        while (currVal != target) {
            i += 1
            currVal = (0..childrenPerGen).map { mutate(currVal, mutationRate) }.maxBy { fitness(it) }!!
        }

        // Check that the algorithm eventually finds the target string
        assertEquals(target, currVal)
    }
}
