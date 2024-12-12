import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EvolutionaryAlgorithmTest {

    @Test
    fun testRandomChar() {
        val validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ "
        repeat(100) {
            val char = randomChar()
            assert(validChars.contains(char)) { "Generated character $char is not valid." }
        }
    }

    @Test
    fun testHammingDistance() {
        assertEquals(0, hammingDistance("SAME", "SAME"))
        assertEquals(4, hammingDistance("DIFF", "SAME"))
        assertEquals(1, hammingDistance("TEST", "TAST"))
        assertEquals(3, hammingDistance("HELLO", "HEXXO"))
    }

    @Test
    fun testFitness() {
        val target = "METHINKS IT IS LIKE A WEASEL"
        assertEquals(target.length, fitness(target))
        assertEquals(0, fitness("DIFFERENT STRING"))
        assertEquals(1, fitness("METHINKS IT IS LIKE A WEASEK"))
    }

    @Test
    fun testMutate() {
        val original = "METHINKS IT IS LIKE A WEASEL"
        val mutationRate = 0.0
        assertEquals(original, mutate(original, mutationRate))

        val highMutationRate = 1.0
        val mutated = mutate(original, highMutationRate)
        assert(mutated != original) { "Mutation with high rate should change the string." }
    }

    @Test
    fun testEvolutionaryAlgorithm() {
        val target = "METHINKS IT IS LIKE A WEASEL"
        val mutationRate = 0.05
        val childrenPerGen = 50

        var currVal = (0 until target.length).map { randomChar() }.joinToString(separator = "")
        var generations = 0
        while (currVal != target) {
            generations++
            currVal = (0..childrenPerGen).map { mutate(currVal, mutationRate) }.maxBy { fitness(it) }!!
        }

        assertEquals(target, currVal)
        println("Evolution found target after $generations generations")
    }
}
