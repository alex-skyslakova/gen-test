import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DinesmanTest {

    private val occupants = listOf("Baker", "Cooper", "Fletcher", "Miller", "Smith")

    @Test
    fun testDinesmanSingleSolution() {
        val predicates = listOf<Predicate>(
            { it.last() != "Baker" },
            { it.first() != "Cooper" },
            { it.last() != "Fletcher" && it.first() != "Fletcher" },
            { it.indexOf("Miller") > it.indexOf("Cooper") },
            { Math.abs(it.indexOf("Smith") - it.indexOf("Fletcher")) > 1 },
            { Math.abs(it.indexOf("Fletcher") - it.indexOf("Cooper")) > 1 }
        )

        val solutions = dinesman(occupants, predicates)
        assertEquals(1, solutions.size)

        val expectedSolution = listOf("Smith", "Cooper", "Baker", "Fletcher", "Miller")
        assertEquals(expectedSolution, solutions[0])
    }


    @Test
    fun testDinesmanNoSolution() {
        val predicates = listOf<Predicate>(
            { it.last() != "Baker" },
            { it.first() != "Cooper" },
            { it[1] == "Fletcher"}, // Conflicting predicate: Fletcher MUST be on the 2nd floor
            { it.indexOf("Miller") > it.indexOf("Cooper") },
            { Math.abs(it.indexOf("Smith") - it.indexOf("Fletcher")) > 1 },
            { Math.abs(it.indexOf("Fletcher") - it.indexOf("Cooper")) > 1 }
        )

        val solutions = dinesman(occupants, predicates)
        assertEquals(0, solutions.size)
    }

    @Test
    fun testDinesmanMultipleSolutions() {
        val predicates = listOf<Predicate>( // Relaxed constraints
            { it.last() != "Baker" },
            { it.first() != "Cooper" }
        )

        val solutions = dinesman(occupants, predicates)
        assertTrue(solutions.size > 1) // Should be multiple solutions with relaxed constraints
    }

    @Test
    fun testPermuteSingleElement() {
        val input = listOf("A")
        val permutations = permute(input)
        assertEquals(1, permutations.size)
        assertEquals(listOf(listOf("A")), permutations)
    }

    @Test
    fun testPermuteMultipleElements() {
        val input = listOf("A", "B", "C")
        val permutations = permute(input)
        assertEquals(6, permutations.size)
        assertTrue(permutations.contains(listOf("A", "B", "C")))
        assertTrue(permutations.contains(listOf("A", "C", "B")))
        assertTrue(permutations.contains(listOf("B", "A", "C")))
        assertTrue(permutations.contains(listOf("B", "C", "A")))
        assertTrue(permutations.contains(listOf("C", "A", "B")))
        assertTrue(permutations.contains(listOf("C", "B", "A")))
    }

}


