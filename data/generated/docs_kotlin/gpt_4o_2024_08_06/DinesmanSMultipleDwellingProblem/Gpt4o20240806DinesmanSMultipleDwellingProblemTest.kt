import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DinesmanMultipleDwellingProblemTest {

    private val occupants = listOf("Baker", "Cooper", "Fletcher", "Miller", "Smith")

    private val predicates = listOf<Predicate>(
        { it.last() != "Baker" },
        { it.first() != "Cooper" },
        { it.last() != "Fletcher" && it.first() != "Fletcher" },
        { it.indexOf("Miller") > it.indexOf("Cooper") },
        { Math.abs(it.indexOf("Smith") - it.indexOf("Fletcher")) > 1 },
        { Math.abs(it.indexOf("Fletcher") - it.indexOf("Cooper")) > 1 }
    )

    @Test
    fun testDinesmanProblem() {
        val expectedSolution = listOf(
            listOf("Miller", "Cooper", "Smith", "Baker", "Fletcher")
        )
        val solutions = dinesman(occupants, predicates)
        assertEquals(expectedSolution, solutions)
    }

    @Test
    fun testNoSolution() {
        val invalidPredicates = listOf<Predicate>(
            { it.last() == "Baker" }, // Invalid condition
            { it.first() != "Cooper" },
            { it.last() != "Fletcher" && it.first() != "Fletcher" },
            { it.indexOf("Miller") > it.indexOf("Cooper") },
            { Math.abs(it.indexOf("Smith") - it.indexOf("Fletcher")) > 1 },
            { Math.abs(it.indexOf("Fletcher") - it.indexOf("Cooper")) > 1 }
        )
        val solutions = dinesman(occupants, invalidPredicates)
        assertEquals(0, solutions.size)
    }

    @Test
    fun testMultipleSolutions() {
        val flexiblePredicates = listOf<Predicate>(
            { it.last() != "Baker" },
            { it.first() != "Cooper" },
            { it.last() != "Fletcher" && it.first() != "Fletcher" },
            { it.indexOf("Miller") > it.indexOf("Cooper") },
            { Math.abs(it.indexOf("Smith") - it.indexOf("Fletcher")) > 1 }
            // Removed one condition to allow multiple solutions
        )
        val solutions = dinesman(occupants, flexiblePredicates)
        assertEquals(2, solutions.size) // Expecting multiple solutions
    }
}
