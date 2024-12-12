import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CyclotomicPolynomialTest {

    @Test
    void testCyclotomicPolynomialFirst30() {
        String[] expectedPolynomials = {
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
            "x^8 - x^7 + x^6 - x^5 + x^4 - x^3 + x^2 - x + 1",
            "x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^6 - x^2 + 1",
            "x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^8 - x^6 + x^4 - x^2 + 1",
            "x^12 - x^6 + 1",
            "x^10 - x^5 + 1",
            "x^22 + x^21 + x^20 + x^19 + x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^20 + x^19 + x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^12 - x^4 + 1",
            "x^18 - x^9 + 1",
            "x^28 + x^27 + x^26 + x^25 + x^24 + x^23 + x^22 + x^21 + x^20 + x^19 + x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1",
            "x^30 + x^29 + x^28 + x^27 + x^26 + x^25 + x^24 + x^23 + x^22 + x^21 + x^20 + x^19 + x^18 + x^17 + x^16 + x^15 + x^14 + x^13 + x^12 + x^11 + x^10 + x^9 + x^8 + x^7 + x^6 + x^5 + x^4 + x^3 + x^2 + x + 1"
        };

        for (int i = 1; i <= 30; i++) {
            Polynomial p = CyclotomicPolynomial.cyclotomicPolynomial(i);
            assertEquals(expectedPolynomials[i - 1], p.toString(), "Mismatch for cyclotomic polynomial CP[" + i + "]");
        }
    }

    @Test
    void testCyclotomicPolynomialOrderWithCoefficient() {
        int[] expectedOrders = {3, 4, 6, 8, 10, 12, 14, 18, 20, 24};

        int n = 0;
        for (int i = 1; i <= 10; i++) {
            while (true) {
                n++;
                Polynomial cyclo = CyclotomicPolynomial.cyclotomicPolynomial(n);
                if (cyclo.hasCoefficientAbs(i)) {
                    assertEquals(expectedOrders[i - 1], n, "Mismatch for smallest cyclotomic polynomial with coefficient magnitude " + i);
                    n--;
                    break;
                }
            }
        }
    }
}
