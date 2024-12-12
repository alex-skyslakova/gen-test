import org.junit.Test
import org.junit.Assert.*
import java.math.BigInteger

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
            listOf(Term(88, 1, 172), Term(51, 1, 239), Term(32, 1, 682), Term(44, 1, 5357), Term(68, 1, 12943)),
            listOf(Term(88, 1, 172), Term(51, 1, 239), Term(32, 1, 682), Term(44, 1, 5357), Term(68, 1, 12944))
        )

        val expectedResults = listOf(
            true,  // π/4 = arctan(1/2) + arctan(1/3)
            true,  // π/4 = 2*arctan(1/3) + arctan(1/7)
            true,  // π/4 = 4*arctan(1/5) - arctan(1/239)
            true,  // π/4 = 5*arctan(1/7) + 2*arctan(3/79)
            true,  // π/4 = 5*arctan(29/278) + 7*arctan(3/79)
            true,  // π/4 = arctan(1/2) + arctan(1/5) + arctan(1/8)
            true,  // π/4 = 4*arctan(1/5) - arctan(1/70) + arctan(1/99)
            true,  // π/4 = 5*arctan(1/7) + 4*arctan(1/53) + 2*arctan(1/4443)
            true,  // π/4 = 6*arctan(1/8) + 2*arctan(1/57) + arctan(1/239)
            true,  // π/4 = 8*arctan(1/10) - arctan(1/239) - 4*arctan(1/515)
            true,  // π/4 = 12*arctan(1/18) + 8*arctan(1/57) - 5*arctan(1/239)
            true,  // π/4 = 16*arctan(1/21) + 3*arctan(1/239) + 4*arctan(3/1042)
            true,  // π/4 = 22*arctan(1/28) + 2*arctan(1/443) - 5*arctan(1/1393) - 10*arctan(1/11018)
            true,  // π/4 = 22*arctan(1/38) + 17*arctan(7/601) + 10*arctan(7/8149)
            true,  // π/4 = 44*arctan(1/57) + 7*arctan(1/239) - 12*arctan(1/682) + 24*arctan(1/12943)
            true,  // π/4 = 88*arctan(1/172) + 51*arctan(1/239) + 32*arctan(1/682) + 44*arctan(1/5357) + 68*arctan(1/12943)
            false  // π/4 = 88*arctan(1/172) + 51*arctan(1/239) + 32*arctan(1/682) + 44*arctan(1/5357) + 68*arctan(1/12944)
        )

        for ((index, terms) in termsList.withIndex()) {
            val result = tanSum(terms) == BigRational.ONE
            assertEquals(expectedResults[index], result)
        }
    }
}
