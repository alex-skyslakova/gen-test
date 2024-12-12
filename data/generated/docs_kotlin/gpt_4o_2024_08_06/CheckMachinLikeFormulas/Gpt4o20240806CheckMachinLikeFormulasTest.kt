import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MachinLikeFormulasTest {

    @Test
    fun testMachinLikeFormulas() {
        val termsList = listOf(
            listOf(Term(1, 1, 2), Term(1, 1, 3)),
            listOf(Term(2, 1, 3), Term(1, 1, 7)),
            listOf(Term(4, 1, 5), Term(-1, 1, 239)),
            listOf(Term(5, 1, 7), Term(2, 3, 79)),
            listOf(Term(5, 29, 278), Term(7, 3, 79)),
            listOf(Term(1, 1, 2), Term(1, 1, 5), Term(1, 1, 8)),
            listOf(Term(4, 1, 5), Term(-1, 1, 70), Term(1, 1, 99)),
            listOf(Term(5, 1, 7), Term(4, 1, 53), Term(2, 1, 4443)),
            listOf(Term(6, 1, 8), Term(2, 1, 57), Term(1, 1, 239)),
            listOf(Term(8, 1, 10), Term(-1, 1, 239), Term(-4, 1, 515)),
            listOf(Term(12, 1, 18), Term(8, 1, 57), Term(-5, 1, 239)),
            listOf(Term(16, 1, 21), Term(3, 1, 239), Term(4, 3, 1042)),
            listOf(Term(22, 1, 28), Term(2, 1, 443), Term(-5, 1, 1393), Term(-10, 1, 11018)),
            listOf(Term(22, 1, 38), Term(17, 7, 601), Term(10, 7, 8149)),
            listOf(Term(44, 1, 57), Term(7, 1, 239), Term(-12, 1, 682), Term(24, 1, 12943)),
            listOf(Term(88, 1, 172), Term(51, 1, 239), Term(32, 1, 682), Term(44, 1, 5357), Term(68, 1, 12943))
        )

        for (terms in termsList) {
            assertEquals(BigRational.ONE, tanSum(terms), "Failed for terms: $terms")
        }
    }

    @Test
    fun testIncorrectMachinLikeFormula() {
        val incorrectTerms = listOf(
            Term(88, 1, 172), Term(51, 1, 239), Term(32, 1, 682), Term(44, 1, 5357), Term(68, 1, 12944)
        )

        assertEquals(false, tanSum(incorrectTerms) == BigRational.ONE, "Incorrect formula should not equal 1")
    }
}
