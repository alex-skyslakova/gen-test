import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BestShuffleTest {

    @Test
    fun testShuffleAbracadabra() {
        val input = "abracadabra"
        val result = BestShuffle(input)
        val (original, shuffled, score) = parseResult(result)
        assertEquals(input, original)
        assertEquals(input.length, shuffled.length)
        assert(shuffled != input) // Ensure it's shuffled
        assert(score.toInt() < input.length) // Ensure not all characters are in the same position
    }

    @Test
    fun testShuffleSeesaw() {
        val input = "seesaw"
        val result = BestShuffle(input)
        val (original, shuffled, score) = parseResult(result)
        assertEquals(input, original)
        assertEquals(input.length, shuffled.length)
        assert(shuffled != input)
        assert(score.toInt() < input.length)
    }

    @Test
    fun testShuffleElk() {
        val input = "elk"
        val result = BestShuffle(input)
        val (original, shuffled, score) = parseResult(result)
        assertEquals(input, original)
        assertEquals(input.length, shuffled.length)
        assert(shuffled != input)
        assert(score.toInt() < input.length)
    }

    @Test
    fun testShuffleGrrrrrr() {
        val input = "grrrrrr"
        val result = BestShuffle(input)
        val (original, shuffled, score) = parseResult(result)
        assertEquals(input, original)
        assertEquals(input.length, shuffled.length)
        assert(shuffled != input)
        assert(score.toInt() < input.length)
    }

    @Test
    fun testShuffleUp() {
        val input = "up"
        val result = BestShuffle(input)
        val (original, shuffled, score) = parseResult(result)
        assertEquals(input, original)
        assertEquals(input.length, shuffled.length)
        assert(shuffled != input)
        assert(score.toInt() < input.length)
    }

    @Test
    fun testShuffleSingleCharacter() {
        val input = "a"
        val result = BestShuffle(input)
        val (original, shuffled, score) = parseResult(result)
        assertEquals(input, original)
        assertEquals(input.length, shuffled.length)
        assertEquals(input, shuffled) // Single character cannot be shuffled
        assertEquals(1, score.toInt()) // Score should be 1 as it cannot change position
    }

    private fun parseResult(result: String): Triple<String, String, String> {
        val parts = result.split(" ", "(", ")")
        return Triple(parts[0], parts[1], parts[2])
    }
}
