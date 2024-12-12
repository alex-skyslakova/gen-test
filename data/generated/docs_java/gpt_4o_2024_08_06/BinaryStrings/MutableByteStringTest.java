import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MutableByteStringTest {

    @Test
    public void testStringCreation() {
        MutableByteString mbs = new MutableByteString((byte) 1, (byte) 2, (byte) 3);
        assertEquals(3, mbs.length());
        assertEquals((byte) 1, mbs.get(0));
        assertEquals((byte) 2, mbs.get(1));
        assertEquals((byte) 3, mbs.get(2));
    }

    @Test
    public void testStringAssignment() {
        MutableByteString mbs = new MutableByteString((byte) 1, (byte) 2, (byte) 3);
        mbs.set(new byte[]{(byte) 4, (byte) 5});
        assertEquals(2, mbs.length());
        assertEquals((byte) 4, mbs.get(0));
        assertEquals((byte) 5, mbs.get(1));
    }

    @Test
    public void testStringComparison() {
        MutableByteString mbs1 = new MutableByteString((byte) 1, (byte) 2, (byte) 3);
        MutableByteString mbs2 = new MutableByteString((byte) 1, (byte) 2, (byte) 3);
        MutableByteString mbs3 = new MutableByteString((byte) 4, (byte) 5, (byte) 6);
        assertTrue(mbs1.regionEquals(0, mbs2, 0, 3));
        assertFalse(mbs1.regionEquals(0, mbs3, 0, 3));
    }

    @Test
    public void testStringCloningAndCopying() {
        MutableByteString mbs1 = new MutableByteString((byte) 1, (byte) 2, (byte) 3);
        MutableByteString mbs2 = mbs1.substring(0, 3);
        assertEquals(mbs1.length(), mbs2.length());
        assertTrue(mbs1.regionEquals(0, mbs2, 0, 3));
    }

    @Test
    public void testCheckIfStringIsEmpty() {
        MutableByteString mbs = new MutableByteString();
        assertTrue(mbs.isEmpty());
        mbs.append((byte) 1);
        assertFalse(mbs.isEmpty());
    }

    @Test
    public void testAppendByteToString() {
        MutableByteString mbs = new MutableByteString((byte) 1, (byte) 2);
        mbs.append((byte) 3);
        assertEquals(3, mbs.length());
        assertEquals((byte) 3, mbs.get(2));
    }

    @Test
    public void testExtractSubstring() {
        MutableByteString mbs = new MutableByteString((byte) 1, (byte) 2, (byte) 3, (byte) 4);
        MutableByteString sub = mbs.substring(1, 3);
        assertEquals(2, sub.length());
        assertEquals((byte) 2, sub.get(0));
        assertEquals((byte) 3, sub.get(1));
    }

    @Test
    public void testReplaceByteInString() {
        MutableByteString mbs = new MutableByteString((byte) 1, (byte) 2, (byte) 1, (byte) 3);
        mbs.replace(new byte[]{(byte) 1}, new byte[]{(byte) 9});
        assertEquals((byte) 9, mbs.get(0));
        assertEquals((byte) 9, mbs.get(2));
    }

    @Test
    public void testReplaceStringInString() {
        MutableByteString mbs = new MutableByteString((byte) 1, (byte) 2, (byte) 1, (byte) 3);
        mbs.replace(new byte[]{(byte) 1, (byte) 2}, new byte[]{(byte) 9, (byte) 8});
        assertEquals((byte) 9, mbs.get(0));
        assertEquals((byte) 8, mbs.get(1));
    }

    @Test
    public void testToHexString() {
        MutableByteString mbs = new MutableByteString((byte) 0x0F, (byte) 0xA0);
        assertEquals("0fa0", mbs.toHexString());
    }

    @Test
    public void testToStringUtf8() {
        MutableByteString mbs = new MutableByteString("hello".getBytes(StandardCharsets.UTF_8));
        assertEquals("hello", mbs.toStringUtf8());
    }
}
