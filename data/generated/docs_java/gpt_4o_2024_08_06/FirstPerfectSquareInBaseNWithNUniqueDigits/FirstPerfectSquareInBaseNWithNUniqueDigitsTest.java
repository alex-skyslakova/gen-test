import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    @Test
    void testIndexOf() {
        assertEquals(0, Program.indexOf('0'));
        assertEquals(9, Program.indexOf('9'));
        assertEquals(10, Program.indexOf('A'));
        assertEquals(35, Program.indexOf('Z'));
        assertEquals(-1, Program.indexOf('!')); // Character not in ALPHABET
    }

    @Test
    void testToStr() {
        Program.base = 10;
        assertEquals("10", Program.toStr(BigInteger.valueOf(10)));
        assertEquals("100", Program.toStr(BigInteger.valueOf(100)));

        Program.base = 16;
        assertEquals("A", Program.toStr(BigInteger.valueOf(10)));
        assertEquals("64", Program.toStr(BigInteger.valueOf(100)));
    }

    @Test
    void testAllInQS() {
        Program.base = 10;
        Program.bllim = BigInteger.valueOf(100);
        Program.ic = 0;
        Program.o = new HashSet<>();
        assertTrue(Program.allInQS(BigInteger.valueOf(1234567890L)));
        assertFalse(Program.allInQS(BigInteger.valueOf(123456789L)));
    }

    @Test
    void testAllInS() {
        Program.base = 10;
        Program.bllim = BigInteger.valueOf(100);
        Program.o = new HashSet<>();
        assertTrue(Program.allInS(BigInteger.valueOf(1234567890L)));
        assertFalse(Program.allInS(BigInteger.valueOf(123456789L)));
    }

    @Test
    void testAllInQ() {
        Program.base = 10;
        assertTrue(Program.allInQ(BigInteger.valueOf(1234567890L)));
        assertFalse(Program.allInQ(BigInteger.valueOf(123456789L)));
    }

    @Test
    void testAllIn() {
        Program.base = 10;
        assertTrue(Program.allIn(BigInteger.valueOf(1234567890L)));
        assertFalse(Program.allIn(BigInteger.valueOf(123456789L)));
    }

    @Test
    void testTo10() {
        Program.base = 10;
        assertEquals(BigInteger.valueOf(123), Program.to10("123"));

        Program.base = 16;
        assertEquals(BigInteger.valueOf(255), Program.to10("FF"));
    }

    @Test
    void testFixup() {
        Program.base = 10;
        assertEquals("1023456789", Program.fixup(0));
        assertEquals("1023456789", Program.fixup(1));

        Program.base = 16;
        assertEquals("1023456789ABCDEF", Program.fixup(0));
    }

    @Test
    void testCheck() {
        Program.base = 10;
        Program.blim = 9;
        Program.ms = "1023456789";
        Program.threshold = BigInteger.valueOf(10000000000L);
        Program.limits = new ArrayList<>();
        Program.limits.add(BigInteger.valueOf(10000000000L));
        Program.limits.add(BigInteger.valueOf(1000000000L));
        Program.o = new HashSet<>();
        Program.o.add((byte) 1);

        Program.check(BigInteger.valueOf(10000000001L));
        assertEquals(8, Program.blim);
    }
}
