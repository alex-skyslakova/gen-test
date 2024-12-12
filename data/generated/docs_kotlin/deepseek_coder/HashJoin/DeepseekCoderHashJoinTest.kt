import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

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

        val expectedTableC = listOf(
            C(A(27, "Jonah"), B("Jonah", "Whales")),
            C(A(27, "Jonah"), B("Jonah", "Spiders")),
            C(A(18, "Alan"), B("Alan", "Ghosts")),
            C(A(18, "Alan"), B("Alan", "Zombies")),
            C(A(28, "Glory"), B("Glory", "Buffy")),
            C(A(28, "Alan"), B("Alan", "Ghosts")),
            C(A(28, "Alan"), B("Alan", "Zombies"))
        )

        val actualTableC = hashJoin(tableA, tableB)

        // Assert that the actual result contains all expected rows
        for (expectedRow in expectedTableC) {
            assertTrue(actualTableC.contains(expectedRow), "Missing row: $expectedRow")
        }

        // Assert that the actual result does not contain any unexpected rows
        for (actualRow in actualTableC) {
            assertTrue(expectedTableC.contains(actualRow), "Unexpected row: $actualRow")
        }
    }
}
