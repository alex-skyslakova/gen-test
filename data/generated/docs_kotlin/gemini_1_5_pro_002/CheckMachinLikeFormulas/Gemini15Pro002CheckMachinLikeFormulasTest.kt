import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.math.BigInteger

class CheckMachinLikeFormulasTest {

    @Test
    fun testTanSum() {
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

        for ((index, terms) in termsList.withIndex()) {
            val expected = if (index < 16) BigRational.ONE else BigRational(BigInteger("2573485229534795951682379362736207473841590522461779191"), BigInteger("2573485229534795951682379362736207473841590522461779188"))
            assertEquals(expected, tanSum(terms), "Test case ${index + 1} failed")
        }
    }


    @Test
    fun testTanEval() {
        assertEquals(BigRational(1, 2), tanEval(1, BigRational(1, 2)))
        assertEquals(BigRational(-1, 2), tanEval(-1, BigRational(1, 2)))
        assertEquals(BigRational(BigInteger("3"), BigInteger("4")), tanEval(2, BigRational(1, 2)),"2*atan(1/2)")
        assertEquals(BigRational(BigInteger("368959"), BigInteger("1882288")), tanEval(3,BigRational(3,79)))
    }

    @Test
    fun testBigRationalArithmetic() {
        val r1 = BigRational(1, 2)
        val r2 = BigRational(1, 3)
        assertEquals(BigRational(5, 6), r1 + r2)
        assertEquals(BigRational(1, 6), r1 - r2)
        assertEquals(BigRational(1, 6), r1 * r2)
        assertEquals(BigRational(3, 2), r1 / r2)
        assertEquals(BigRational(-1, 2), -r1)
        assertEquals(BigRational(2,1), r1.inverse())


    }


}
