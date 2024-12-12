import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class ProgramTest {

    @Test
    void indexOfTest() {
        assertEquals(0, Program.indexOf('0'));
        assertEquals(10, Program.indexOf('A'));
        assertEquals(36, Program.indexOf('a'));
        assertEquals(-1, Program.indexOf('$'));
    }

    @Test
    void toStrTest() {
        Program.base = 2;
        assertEquals("101", Program.toStr(BigInteger.valueOf(5)));
        Program.base = 16;
        assertEquals("A", Program.toStr(BigInteger.valueOf(10)));
    }


    @Test
    void to10Test() {
        Program.base = 2;
        assertEquals(BigInteger.valueOf(5), Program.to10("101"));
        Program.base = 16;
        assertEquals(BigInteger.valueOf(10), Program.to10("A"));
    }

    @Test
    void fixupTest() {
        Program.base = 3;
        assertEquals("10012", Program.fixup(0));
        assertEquals("10012", Program.fixup(1)); // Should behave same for small n<=1 as for n=0
        assertEquals("1201",Program.fixup(2));
    }

    @Test
    void allInTest() {
        Program.base = 3;
        assertTrue(Program.allIn(BigInteger.valueOf(13))); // 111 in base 3
        assertFalse(Program.allIn(BigInteger.valueOf(4))); // 11 in base 3
    }


    @Test
    void allInQTest() {
        Program.base = 3;
        assertTrue(Program.allInQ(BigInteger.valueOf(13)));
        assertFalse(Program.allInQ(BigInteger.valueOf(4)));
    }

    @Test
    void allInSTest() {
        Program.base = 4;
        Program.ms = "100123";
        Program.blim = 1; // Pretend blim is 1, so "10" is the prefix
        Program.o = new HashSet<>(Set.of((byte)Program.indexOf('1'),(byte)Program.indexOf('0')));
        assertTrue(Program.allInS(BigInteger.valueOf(45))); //10213 in base4, 10 is preset
        assertFalse(Program.allInS(BigInteger.valueOf(42))); // 1020 in base4, 10 is preset, missing 3.
    }

    @Test
    void allInQSTest() {
        Program.base = 4;
        Program.ms = "100123";
        Program.blim = 1; // Pretend blim is 1, so "10" is the prefix
        Program.ic = 2;
        Program.o = new HashSet<>(Set.of((byte)Program.indexOf('1'),(byte)Program.indexOf('0')));
        assertTrue(Program.allInQS(BigInteger.valueOf(45))); //10213 in base4, 10 is preset
        assertFalse(Program.allInQS(BigInteger.valueOf(42))); // 1020 in base4, 10 is preset, missing 3.

    }


    @Test
    void checkTest(){
        Program.base = 3;
        Program.bmo = 2;
        Program.blim = 1;
        Program.ic = 2;
        Program.ms = "1012";
        Program.limits.add(BigInteger.valueOf(8)); // Dummy limits, just for the test.
        Program.limits.add(BigInteger.valueOf(26));

        Program.o = new HashSet<>(Set.of((byte) 0,(byte) 1,(byte)2));

        BigInteger sq = BigInteger.valueOf(10); // initially above threshold
        Program.threshold = BigInteger.valueOf(8);

        Program.check(sq);
        assertEquals(1, Program.blim);
        assertEquals(2, Program.ic);
        assertEquals(BigInteger.valueOf(26),Program.threshold);

        sq = BigInteger.valueOf(30);

        Program.check(sq);
        assertEquals(0, Program.blim);
        assertEquals(1, Program.ic);
        assertEquals(BigInteger.valueOf(8), Program.threshold);
        assertFalse(Program.o.contains((byte)Program.indexOf('1')));
    }


    @Test
    void formatTest() {
        assertEquals("00:00.001", Program.format(java.time.Duration.ofMillis(1)));
        assertEquals("00:01.000", Program.format(java.time.Duration.ofSeconds(1)));
        assertEquals("01:00.000", Program.format(java.time.Duration.ofMinutes(1)));
    }



}
