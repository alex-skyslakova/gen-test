import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class HashJoinTest {

    data class A(val age: Int, val name: String)

    data class B(val character: String, val nemesis: String)

    data class C(val rowA: A, val rowB: B)

    fun hashJoin(tableA: List<A>, tableB: List<B>): List<C> {
        val mm = tableB.groupBy { it.character }
        val tableC = mutableListOf<C>()
        for (a in tableA) {
            val value = mm[a.name] ?: continue
            for (b in value) tableC.add(C(a, b))
        }
        return tableC.toList()
    }


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
        val expected = listOf(
            C(A(27, "Jonah"), B("Jonah", "Whales")),
            C(A(27, "Jonah"), B("Jonah", "Spiders")),
            C(A(18, "Alan"), B("Alan", "Ghosts")),
            C(A(18, "Alan"), B("Alan", "Zombies")),
            C(A(28, "Glory"), B("Glory", "Buffy")),
            C(A(28, "Alan"), B("Alan", "Ghosts")),
            C(A(28, "Alan"), B("Alan", "Zombies"))
        )
        val actual = hashJoin(tableA, tableB)

       assertEquals(expected.size, actual.size)
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected)) // Order doesn't matter


    }

    @Test
    fun testHashJoinEmptyA() {
        val tableA = emptyList<A>()
        val tableB = listOf(
            B("Jonah", "Whales"),
            B("Jonah", "Spiders")
        )
        val expected = emptyList<C>()
        val actual = hashJoin(tableA, tableB)
        assertEquals(expected, actual)
    }

    @Test
    fun testHashJoinEmptyB() {
        val tableA = listOf(
            A(27, "Jonah"),
            A(18, "Alan")
        )
        val tableB = emptyList<B>()
        val expected = emptyList<C>()
        val actual = hashJoin(tableA, tableB)
        assertEquals(expected, actual)
    }

    @Test
    fun testHashJoinNoMatches() {
        val tableA = listOf(A(27, "Jonah"))
        val tableB = listOf(B("Alan", "Ghosts"))
        val expected = emptyList<C>()
        val actual = hashJoin(tableA, tableB)
        assertEquals(expected, actual)
    }


}
