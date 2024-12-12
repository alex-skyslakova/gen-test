import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CyclotomicPolynomialTest {

    @Test
    fun testCyclotomicPolynomialFirst30() {
        val expectedPolynomials = listOf(
            "x - 1",
            "x + 1",
            "x^2 + x + 1",
            "x^2 + 1",
            "x^4 + x^3 + x^2 + x + 1",
            "x^2 - x + 1",
            "x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^4 + 1",
            "x^6 + x^3 + 1",
            "x^4 - x^3 + x^2 - x + 1",
            "x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^4 - x^2 + 1",
            "x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^6 - x^3 + 1",
            "x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^8 - x^4 + 1",
            "x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^6 - x^2 + 1",
            "x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^8 - x^6 + x^4 - x^2 + 1",
            "x^12 - x^6 + 1",
            "x^10 - x^5 + 1",
            "x^22 + x^21 + x^20 + x^19 + x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^8 - x^4 + 1",
            "x^20 + x^19 + x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^12 - x^4 + 1",
            "x^18 - x^9 + 1",
            "x^28 + x^27 + x^26 + x^25 + x^24 + x^23 + x^22 + x^21 + x^20 + x^19 + x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^8 - x^6 + x^4 - x^2 + 1",
            "x^30 + x^29 + x^28 + x^27 + x^26 + x^25 + x^24 + x^23 + x^22 + x^21 + x^20 + x^19 + x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1"
        )

        for (i in 1..30) {
            val polynomial = cyclotomicPolynomial(i)
            assertEquals(expectedPolynomials[i - 1], polynomial.toString(), "Failed at n = $i")
        }
    }

    @Test
    fun testCyclotomicPolynomialWithCoefficientMagnitude() {
        val expectedOrders = listOf(3, 4, 5, 7, 9, 11, 13, 15, 17, 19)

        var n = 0
        for (i in 1..10) {
            while (true) {
                n++
                val cyclo = cyclotomicPolynomial(n)
                if (cyclo!!.hasCoefficientAbs(i)) {
                    assertEquals(expectedOrders[i - 1], n, "Failed for coefficient magnitude = $i")
                    n--
                    break
                }
            }
        }
    }
}
