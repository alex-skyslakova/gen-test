import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MutableByteStringTest {

    @Test
    public void testCreationAndLength() {
        MutableByteString byteString = new MutableByteString((byte) 0x41, (byte) 0x42);
        assertEquals(2, byteString.length());
    }

    @Test
    public void testIsEmpty() {
        MutableByteString byteString = new MutableByteString();
        assertTrue(byteString.isEmpty());

        byteString.append((byte) 0x41);
        assertFalse(byteString.isEmpty());
    }

    @Test
    public void testGet() {
        MutableByteString byteString = new MutableByteString((byte) 0x41, (byte) 0x42);
        assertEquals(0x41, byteString.get(0));
        assertEquals(0x42, byteString.get(1));
    }

    @Test
    public void testSet() {
        MutableByteString byteString = new MutableByteString((byte) 0x41, (byte) 0x42);
        byteString.set(new byte[]{(byte) 0x43, (byte) 0x44});
        assertEquals(0x43, byteString.get(0));
        assertEquals(0x44, byteString.get(1));
    }

    @Test
    public void testSetAtIndex() {
        MutableByteString byteString = new MutableByteString((byte) 0x41, (byte) 0x42);
        byteString.set(1, (byte) 0x43);
        assertEquals(0x41, byteString.get(0));
        assertEquals(0x43, byteString.get(1));
    }

    @Test
    public void testAppend() {
        MutableByteString byteString = new MutableByteString((byte) 0x41);
        byteString.append((byte) 0x42);
        assertEquals(2, byteString.length());
        assertEquals(0x42, byteString.get(1));
    }

    @Test
    public void testSubstring() {
        MutableByteString byteString = new MutableByteString((byte) 0x41, (byte) 0x42, (byte) 0x43);
        MutableByteString subString = byteString.substring(1, 3);
        assertEquals(2, subString.length());
        assertEquals(0x42, subString.get(0));
        assertEquals(0x43, subString.get(1));
    }

    @Test
    public void testReplace() {
        MutableByteString byteString = new MutableByteString((byte) 0x41, (byte) 0x42, (byte) 0x41);
        byteString.replace(new byte[]{(byte) 0x41}, new byte[]{(byte) 0x43});
        assertEquals(0x43, byteString.get(0));
        assertEquals(0x42, byteString.get(1));
        assertEquals(0x43, byteString.get(2));
    }

    @Test
    public void testRegionEquals() {
        MutableByteString byteString1 = new MutableByteString((byte) 0x41, (byte) 0x42);
        MutableByteString byteString2 = new MutableByteString((byte) 0x41, (byte) 0x42);
        assertTrue(byteString1.regionEquals(0, byteString2, 0, 2));
        assertFalse(byteString1.regionEquals(0, byteString2, 0, 3));
    }

    @Test
    public void testToHexString() {
        MutableByteString byteString = new MutableByteString((byte) 0x41, (byte) 0x42);
        assertEquals("4142", byteString.toHexString());
    }

    @Test
    public void testToStringUtf8() {
        MutableByteString byteString = new MutableByteString((byte) 0x41, (byte) 0x42);
        assertEquals("AB", byteString.toStringUtf8());
    }

    @Test
    public void testCheckIndexOutOfBounds() {
        MutableByteString byteString = new MutableByteString((byte) 0x41);
        assertThrows(IndexOutOfBoundsException.class, () -> byteString.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> byteString.set(1, (byte) 0x42));
    }
}
