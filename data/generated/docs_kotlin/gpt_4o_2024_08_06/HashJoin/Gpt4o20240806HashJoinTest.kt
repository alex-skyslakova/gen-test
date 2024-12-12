import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HashJoinTest {

    @Test
    fun testHashJoin() {
        val tableA = listOf(
            A(27, "Jonah"),
            A(18, "Alan"),
            A(28, "Glory"),
            A(18, "Popeye"),
            A(28, "Alan")
        )
        val tableB = listOf(
            B("Jonah", "Whales"),
            B("Jonah", "Spiders"),
            B("Alan", "Ghosts"),
            B("Alan", "Zombies"),
            B("Glory", "Buffy")
        )
        
        val expectedOutput = listOf(
            C(A(27, "Jonah"), B("Jonah", "Whales")),
            C(A(27, "Jonah"), B("Jonah", "Spiders")),
            C(A(18, "Alan"), B("Alan", "Ghosts")),
            C(A(18, "Alan"), B("Alan", "Zombies")),
            C(A(28, "Glory"), B("Glory", "Buffy")),
            C(A(28, "Alan"), B("Alan", "Ghosts")),
            C(A(28, "Alan"), B("Alan", "Zombies"))
        )

        val actualOutput = hashJoin(tableA, tableB)

        assertEquals(expectedOutput.toSet(), actualOutput.toSet())
    }
}
