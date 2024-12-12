import org.junit.Test
import kotlin.test.assertEquals

class GameOfLifeTest {

    @Test
    fun testBlinkerPattern() {
        val life = Life(Pattern.BLINKER)

        // Generation 0
        assertEquals(
            """
            ...
            ###
            ...
            """.trimIndent(),
            life.toString()
        )

        // Generation 1
        life.step()
        assertEquals(
            """
            .#.
            .#.
            .#.
            """.trimIndent(),
            life.toString()
        )

        // Generation 2
        life.step()
        assertEquals(
            """
            ...
            ###
            ...
            """.trimIndent(),
            life.toString()
        )

        // Generation 3
        life.step()
        assertEquals(
            """
            .#.
            .#.
            .#.
            """.trimIndent(),
            life.toString()
        )
    }

    @Test
    fun testGliderPattern() {
        val life = Life(Pattern.GLIDER)

        // Generation 0
        assertEquals(
            """
            .#.
            ..#
            ###
            """.trimIndent(),
            life.toString()
        )

        // Generation 1
        life.step()
        assertEquals(
            """
            #.#
            .##
            .#.
            """.trimIndent(),
            life.toString()
        )

        // Generation 2
        life.step()
        assertEquals(
            """
            .#..
            #.#.
            .##.
            """.trimIndent(),
            life.toString()
        )

        // Generation 3
        life.step()
        assertEquals(
            """
            ..#.
            #.#.
            .##.
            """.trimIndent(),
            life.toString()
        )
    }

    @Test
    fun testRandomPattern() {
        val life = Life(Pattern.RANDOM)

        // Since the random pattern is, well, random, we can't test specific generations.
        // However, we can ensure that the grid dimensions are correct.
        assertEquals(80, life.w)
        assertEquals(15, life.h)
    }
}
