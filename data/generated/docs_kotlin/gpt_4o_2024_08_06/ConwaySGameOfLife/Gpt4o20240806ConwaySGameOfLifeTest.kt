import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConwaySGameOfLifeTest {

    @Test
    fun testBlinkerPattern() {
        val life = Life(Pattern.BLINKER)

        // Initial state
        val expectedGen0 = """
            ...
            ###
            ...
            
        """.trimIndent()
        assertEquals(expectedGen0, life.toString())

        // First generation
        life.step()
        val expectedGen1 = """
            .#.
            .#.
            .#.
            
        """.trimIndent()
        assertEquals(expectedGen1, life.toString())

        // Second generation
        life.step()
        val expectedGen2 = """
            ...
            ###
            ...
            
        """.trimIndent()
        assertEquals(expectedGen2, life.toString())

        // Third generation
        life.step()
        val expectedGen3 = """
            .#.
            .#.
            .#.
            
        """.trimIndent()
        assertEquals(expectedGen3, life.toString())
    }

    @Test
    fun testGliderPattern() {
        val life = Life(Pattern.GLIDER)

        // Initial state
        val expectedGen0 = """
            .#..
            ..#.
            ###.
            ....
            
        """.trimIndent()
        assertEquals(expectedGen0, life.toString())

        // First generation
        life.step()
        val expectedGen1 = """
            ....
            #.#.
            .##.
            .#..
            
        """.trimIndent()
        assertEquals(expectedGen1, life.toString())

        // Second generation
        life.step()
        val expectedGen2 = """
            ....
            ..#.
            #.#.
            .##.
            
        """.trimIndent()
        assertEquals(expectedGen2, life.toString())

        // Third generation
        life.step()
        val expectedGen3 = """
            ....
            .#..
            ..##
            .##.
            
        """.trimIndent()
        assertEquals(expectedGen3, life.toString())
    }

    @Test
    fun testRandomPattern() {
        val life = Life(Pattern.RANDOM)
        // Since the random pattern is unpredictable, we will not test specific generations.
        // Instead, we will ensure that the grid is initialized and can step without errors.
        life.step()
        life.step()
        life.step()
    }
}
