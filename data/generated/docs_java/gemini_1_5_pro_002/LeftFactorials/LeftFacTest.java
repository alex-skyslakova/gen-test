import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

public class LeftFacTest {

    @Test
    void testFactorialZero() {
        assertEquals(BigInteger.ONE, LeftFac.factorial(BigInteger.ZERO));
    }

    @Test
    void testFactorialOne() {
        assertEquals(BigInteger.ONE, LeftFac.factorial(BigInteger.ONE));
    }

    @Test
    void testFactorialFive() {
        assertEquals(BigInteger.valueOf(120), LeftFac.factorial(BigInteger.valueOf(5)));
    }


    @Test
    void testLeftFactZero() {
        assertEquals(BigInteger.ZERO, LeftFac.leftFact(BigInteger.ZERO));
    }

    @Test
    void testLeftFactOne() {
        assertEquals(BigInteger.ONE, LeftFac.leftFact(BigInteger.ONE));
    }

    @Test
    void testLeftFactTwo() {
        assertEquals(BigInteger.ONE, LeftFac.leftFact(BigInteger.valueOf(2)));
    }

    @Test
    void testLeftFactThree() {
        assertEquals(BigInteger.valueOf(3), LeftFac.leftFact(BigInteger.valueOf(3)));
    }

    @Test
    void testLeftFactFour() {
        assertEquals(BigInteger.valueOf(9), LeftFac.leftFact(BigInteger.valueOf(4)));
    }

    @Test
    void testLeftFactFive() {
        assertEquals(BigInteger.valueOf(33), LeftFac.leftFact(BigInteger.valueOf(5)));
    }

    @Test
    void testLeftFactTen() {
        assertEquals(BigInteger.valueOf(409113), LeftFac.leftFact(BigInteger.valueOf(10)));
    }

    @Test
    void testLeftFactTwenty(){
        assertEquals(new BigInteger("2601218943565795100204973609718191203715864337718569206108361526400"), LeftFac.leftFact(BigInteger.valueOf(20)));

    }
}
