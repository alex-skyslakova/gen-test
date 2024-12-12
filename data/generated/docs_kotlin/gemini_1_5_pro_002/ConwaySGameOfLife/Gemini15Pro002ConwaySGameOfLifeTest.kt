import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LifeTest {

    @Test
    fun testBlinkerThreeGenerations() {
        val life = Life(Pattern.BLINKER)
        val expectedGen0 = """
            ...
            ###
            ...
        """.trimIndent()
        assertEquals(expectedGen0, life.toString())

        life.step()
        val expectedGen1 = """
            .#.
            .#.
            .#.
        """.trimIndent()
        assertEquals(expectedGen1, life.toString())

        life.step()
        val expectedGen2 = """
            ...
            ###
            ...
        """.trimIndent()
        assertEquals(expectedGen2, life.toString())

        life.step()
        val expectedGen3 = """
            .#.
            .#.
            .#.
        """.trimIndent()

        assertEquals(expectedGen3, life.toString())


    }

    @Test
    fun testGliderFourGenerations() {
        val life = Life(Pattern.GLIDER)
        val expectedGen0 = """
            .#..
            ..#.
            ###.
            ....
        """.trimIndent()
        assertEquals(expectedGen0, life.toString())


        life.step()
        val expectedGen1 = """
            ....
            .#.#
            ..##
            ....

        """.trimIndent()
        assertEquals(expectedGen1, life.toString())


        life.step()

        val expectedGen2 = """
            ....
            ..#.
            .##.
            .#..

        """.trimIndent()
        assertEquals(expectedGen2, life.toString())

        life.step()
        val expectedGen3 = """
            ....
            ...#
            #...
            .##.

        """.trimIndent()
        assertEquals(expectedGen3, life.toString())

        life.step()
        val expectedGen4 = """
            ....
            .#..
            ..##
            ...#
        """.trimIndent()
        assertEquals(expectedGen4, life.toString())
    }




    @Test
    fun testFieldState() {
        val field = Field(3, 3)
        field[1, 1] = true
        assertTrue(field.state(1, 1))
        assertFalse(field.state(0, 0))
        assertFalse(field.state(-1, 0))
        assertFalse(field.state(0, -1))
        assertFalse(field.state(3, 1))
        assertFalse(field.state(1, 3))

    }


    @Test
    fun testFieldNext() {
        val field = Field(3, 3)
        field[0, 1] = true
        field[1, 1] = true
        field[2, 1] = true

        assertTrue(field.next(1,0))
        assertTrue(field.next(1,2))
        assertFalse(field.next(1,1))
        assertFalse(field.next(0,0))

    }


    @Test
    fun testRandomPatternInitialization() {
        val life = Life(Pattern.RANDOM)
        assertEquals(80, life.w)
        assertEquals(15, life.h)
        // Check that some cells are alive, but don't enforce a specific count
        // due to randomness.  At least one should be alive given the initialization logic.
        var atLeastOneAlive = false
        for (y in 0 until life.h) {
            for (x in 0 until life.w) {
                if (life.a.state(x, y)) {
                    atLeastOneAlive = true
                    break
                }
            }
        }
        assertTrue(atLeastOneAlive)
    }




}

