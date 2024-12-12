import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeftFacTest {

    @Test
    public void testFactorial() {
        assertEquals(BigInteger.ONE, LeftFac.factorial(BigInteger.ZERO));
        assertEquals(BigInteger.ONE, LeftFac.factorial(BigInteger.ONE));
        assertEquals(BigInteger.valueOf(2), LeftFac.factorial(BigInteger.valueOf(2)));
        assertEquals(BigInteger.valueOf(6), LeftFac.factorial(BigInteger.valueOf(3)));
        assertEquals(BigInteger.valueOf(24), LeftFac.factorial(BigInteger.valueOf(4)));
        assertEquals(BigInteger.valueOf(120), LeftFac.factorial(BigInteger.valueOf(5)));
    }

    @Test
    public void testLeftFact() {
        assertEquals(BigInteger.ZERO, LeftFac.leftFact(BigInteger.ZERO));
        assertEquals(BigInteger.ZERO, LeftFac.leftFact(BigInteger.ONE));
        assertEquals(BigInteger.ONE, LeftFac.leftFact(BigInteger.valueOf(2)));
        assertEquals(BigInteger.valueOf(2), LeftFac.leftFact(BigInteger.valueOf(3)));
        assertEquals(BigInteger.valueOf(4), LeftFac.leftFact(BigInteger.valueOf(4)));
        assertEquals(BigInteger.valueOf(10), LeftFac.leftFact(BigInteger.valueOf(5)));
        assertEquals(BigInteger.valueOf(153), LeftFac.leftFact(BigInteger.valueOf(6)));
        assertEquals(BigInteger.valueOf(873), LeftFac.leftFact(BigInteger.valueOf(7)));
        assertEquals(BigInteger.valueOf(5913), LeftFac.leftFact(BigInteger.valueOf(8)));
        assertEquals(BigInteger.valueOf(46233), LeftFac.leftFact(BigInteger.valueOf(9)));
        assertEquals(BigInteger.valueOf(409113), LeftFac.leftFact(BigInteger.valueOf(10)));
        assertEquals(BigInteger.valueOf(14833), LeftFac.leftFact(BigInteger.valueOf(20)));
        assertEquals(BigInteger.valueOf(2561327494111820313L), LeftFac.leftFact(BigInteger.valueOf(30)));
        assertEquals(new BigInteger("10333147966386144929666651337523200000000"), LeftFac.leftFact(BigInteger.valueOf(40)));
        assertEquals(new BigInteger("33452526613163807108170062053440751665152000000000"), LeftFac.leftFact(BigInteger.valueOf(50)));
        assertEquals(new BigInteger("148362637348470135821287825000000000000000000000000"), LeftFac.leftFact(BigInteger.valueOf(60)));
        assertEquals(new BigInteger("620448401733239439360000000000000000000000000000000000"), LeftFac.leftFact(BigInteger.valueOf(70)));
        assertEquals(new BigInteger("3307885441519386412259530282212537821456832518209340000000000000000000000"), LeftFac.leftFact(BigInteger.valueOf(80)));
        assertEquals(new BigInteger("188267213102014353780000000000000000000000000000000000000000000000000000000"), LeftFac.leftFact(BigInteger.valueOf(90)));
        assertEquals(new BigInteger("1132428117820629783145752115873204622873174957948820000000000000000000000000000"), LeftFac.leftFact(BigInteger.valueOf(100)));
        assertEquals(new BigInteger("7156945704626380229481153372318653216558465734236570000000000000000000000000000000"), LeftFac.leftFact(BigInteger.valueOf(110)));
    }

    @Test
    public void testLeftFactLength() {
        assertEquals(2568, LeftFac.leftFact(BigInteger.valueOf(1000)).toString().length());
        assertEquals(35660, LeftFac.leftFact(BigInteger.valueOf(2000)).toString().length());
        assertEquals(45997, LeftFac.leftFact(BigInteger.valueOf(3000)).toString().length());
        assertEquals(56572, LeftFac.leftFact(BigInteger.valueOf(4000)).toString().length());
        assertEquals(67367, LeftFac.leftFact(BigInteger.valueOf(5000)).toString().length());
        assertEquals(78367, LeftFac.leftFact(BigInteger.valueOf(6000)).toString().length());
        assertEquals(89567, LeftFac.leftFact(BigInteger.valueOf(7000)).toString().length());
        assertEquals(100964, LeftFac.leftFact(BigInteger.valueOf(8000)).toString().length());
        assertEquals(112555, LeftFac.leftFact(BigInteger.valueOf(9000)).toString().length());
        assertEquals(124337, LeftFac.leftFact(BigInteger.valueOf(10000)).toString().length());
    }
}
