import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class CyclotomicPolynomialTest {

    @Test
    void cyclotomicPolynomial_n1() {
        Polynomial p = CyclotomicPolynomial.cyclotomicPolynomial(1);
        assertEquals("x - 1", p.toString());
    }

    @Test
    void cyclotomicPolynomial_nPrime() {
        Polynomial p = CyclotomicPolynomial.cyclotomicPolynomial(5);
        assertEquals("x^4 + x^3 + x^2 + x + 1", p.toString());
    }

    @Test
    void cyclotomicPolynomial_n2p() {
        Polynomial p = CyclotomicPolynomial.cyclotomicPolynomial(6);
        assertEquals("x^2 - x + 1", p.toString());
    }


    @Test
    void cyclotomicPolynomial_n2h() {
        Polynomial p = CyclotomicPolynomial.cyclotomicPolynomial(8);
        assertEquals("x^4 + 1", p.toString());
    }

    @Test
    void cyclotomicPolynomial_npk() {
        Polynomial p = CyclotomicPolynomial.cyclotomicPolynomial(9);
        assertEquals("x^6 + x^3 + 1", p.toString());
    }
    
    @Test
    void cyclotomicPolynomial_n2hpk() {
        Polynomial p = CyclotomicPolynomial.cyclotomicPolynomial(18);
        assertEquals("x^6 - x^3 + 1", p.toString());
    }

    @Test
    void cyclotomicPolynomial_n2mOdd() {
        Polynomial p = CyclotomicPolynomial.cyclotomicPolynomial(14);
        assertEquals("x^6 - x^5 + x^4 - x^3 + x^2 - x + 1", p.toString());
    }


    @Test
    void cyclotomicPolynomial_generalCase() {
        Polynomial p = CyclotomicPolynomial.cyclotomicPolynomial(12);
        assertEquals("x^4 - x^2 + 1", p.toString());

        Polynomial p2 = CyclotomicPolynomial.cyclotomicPolynomial(30);
        assertEquals("x^8 + x^7 - x^5 - x^4 - x^3 + x + 1", p2.toString());
    }




    @Test
    void getDivisors_test() {
        List<Integer> divisors = CyclotomicPolynomial.getDivisors(12);
        List<Integer> expected = new ArrayList<>(List.of(1, 2, 3, 4, 6));
        assertEquals(expected, divisors);

        divisors = CyclotomicPolynomial.getDivisors(1);
        expected = new ArrayList<>(List.of(1));
        assertEquals(expected, divisors);

         divisors = CyclotomicPolynomial.getDivisors(7);
        expected = new ArrayList<>(List.of(1));
        assertEquals(expected, divisors);

    }

    @Test
    void getFactors_test() {
        Map<Integer, Integer> factors = CyclotomicPolynomial.getFactors(12);
        Map<Integer, Integer> expected = new TreeMap<>();
        expected.put(2, 2);
        expected.put(3, 1);
        assertEquals(expected, factors);


        factors = CyclotomicPolynomial.getFactors(2);
        expected = new TreeMap<>();
        expected.put(2, 1);
        assertEquals(expected, factors);

        factors = CyclotomicPolynomial.getFactors(7);
        expected = new TreeMap<>();
        expected.put(7, 1);
        assertEquals(expected, factors);


    }


    @Test
    void polynomial_test() {
        Polynomial p = new CyclotomicPolynomial.Polynomial(1, 2, -1, 0);
        assertEquals("x^2 - 1", p.toString());

        Polynomial q = new CyclotomicPolynomial.Polynomial(1,1,-1,0);
        Polynomial r = p.divide(q);
        assertEquals("x + 1", r.toString());

        CyclotomicPolynomial.Term term = new CyclotomicPolynomial.Term(2, 2);

        Polynomial multiplied = p.multiply(term);
        assertEquals("2x^4 - 2x^2",multiplied.toString());


        Polynomial sum = p.add(q);
        assertEquals("x^2 + x - 2", sum.toString());



    }


    @Test
    void term_test() {
        CyclotomicPolynomial.Term t1 = new CyclotomicPolynomial.Term(2, 2);
        CyclotomicPolynomial.Term t2 = new CyclotomicPolynomial.Term(1, 2);

        CyclotomicPolynomial.Term sum = t1.add(t2);
        assertEquals("3x^2", sum.toString());

        CyclotomicPolynomial.Term multiplied = t1.multiply(t2);
        assertEquals("2x^4", multiplied.toString());


    }


}
