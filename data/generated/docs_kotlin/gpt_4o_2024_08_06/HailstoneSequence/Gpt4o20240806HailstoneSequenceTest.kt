import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.ArrayDeque

class HailstoneSequenceTest {

    @Test
    fun testHailstoneSequenceFor27() {
        val expectedSequenceStart = listOf(27, 82, 41, 124)
        val expectedSequenceEnd = listOf(8, 4, 2, 1)
        val hailstoneSequence = hailstone(27)
        
        assertEquals(112, hailstoneSequence.size, "The sequence length should be 112.")
        assertEquals(expectedSequenceStart, hailstoneSequence.take(4), "The sequence should start with 27, 82, 41, 124.")
        assertEquals(expectedSequenceEnd, hailstoneSequence.takeLast(4), "The sequence should end with 8, 4, 2, 1.")
    }

    @Test
    fun testLongestHailstoneSequenceUnder100000() {
        var longestHail = hailstone(1)
        for (x in 1..99999) {
            longestHail = arrayOf(hailstone(x), longestHail).maxBy { it.size } ?: longestHail
        }
        
        // The known result for the longest sequence under 100,000 is for the number 77031
        assertEquals(77031, longestHail.first, "The number with the longest sequence under 100,000 should be 77031.")
        assertEquals(351, longestHail.size, "The length of the longest sequence should be 351.")
    }
}
