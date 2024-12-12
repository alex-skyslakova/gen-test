import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DinesmanMultipleDwellingProblemTest {

    @Test
    fun testPermute() {
        val input = listOf("A", "B", "C")
        val expectedPermutations = listOf(
            listOf("A", "B", "C"),
            listOf("A", "C", "B"),
            listOf("B", "A", "C"),
            listOf("B", "C", "A"),
            listOf("C", "A", "B"),
            listOf("C", "B", "A")
        )
        val permutations = permute(input)
        assertEquals(expectedPermutations.size, permutations.size)
        assertTrue(expectedPermutations.containsAll(permutations))
        assertTrue(permutations.containsAll(expectedPermutations))
    }

    @Test
    fun testDinesmanNoSolutions() {
        val occupants = listOf("Baker", "Cooper", "Fletcher", "Miller", "Smith")
        val predicates = listOf<Predicate>(
            { it.last() != "Baker" },
            { it.first() != "Cooper" },
            { it.last() != "Fletcher" && it.first() != "Fletcher" },
            { it.indexOf("Miller") > it.indexOf("Cooper") },
            { Math.abs(it.indexOf("Smith") - it.indexOf("Fletcher")) > 1 },
            { Math.abs(it.indexOf("Fletcher") - it.indexOf("Cooper")) > 1 },
            { it.indexOf("Baker") == it.indexOf("Cooper") } // Impossible condition
        )
        val solutions = dinesman(occupants, predicates)
        assertEquals(0, solutions.size)
    }

    @Test
    fun testDinesmanSingleSolution() {
        val occupants = listOf("Baker", "Cooper", "Fletcher", "Miller", "Smith")
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
        val expectedSolution = listOf("Cooper", "Baker", "Smith", "Fletcher", "Miller")
        assertTrue(solutions.contains(expectedSolution))
    }

    @Test
    fun testDinesmanMultipleSolutions() {
        // This test case is unlikely given the problem constraints, but it's here for completeness.
        val occupants = listOf("Baker", "Cooper", "Fletcher", "Miller", "Smith")
        val predicates = listOf<Predicate>(
            { it.last() != "Baker" },
            { it.first() != "Cooper" },
            { it.last() != "Fletcher" && it.first() != "Fletcher" },
            { it.indexOf("Miller") > it.indexOf("Cooper") },
            { Math.abs(it.indexOf("Smith") - it.indexOf("Fletcher")) > 1 },
            { Math.abs(it.indexOf("Fletcher") - it.indexOf("Cooper")) > 1 },
            { it.indexOf("Baker") != it.indexOf("Cooper") } // Redundant condition
        )
        val solutions = dinesman(occupants, predicates)
        assertEquals(1, solutions.size) // Still only one solution due to problem constraints
    }
}
