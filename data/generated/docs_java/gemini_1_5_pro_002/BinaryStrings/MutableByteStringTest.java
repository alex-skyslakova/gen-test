import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class MutableByteStringTest {

    @Test
    void testConstruction() {
        MutableByteString str = new MutableByteString(new byte[]{1, 2, 3});
        assertEquals(3, str.length());
        assertArrayEquals(new byte[]{1, 2, 3}, Arrays.copyOfRange(str.bytes, 0, str.length()));

        MutableByteString emptyStr = new MutableByteString();
        assertEquals(0, emptyStr.length());


    }


    @Test
    void testLength() {
        MutableByteString str = new MutableByteString(new byte[]{1, 2, 3});
        assertEquals(3, str.length());
    }

    @Test
    void testIsEmpty() {
        MutableByteString str = new MutableByteString(new byte[]{1, 2, 3});
        assertFalse(str.isEmpty());

        MutableByteString emptyStr = new MutableByteString();
        assertTrue(emptyStr.isEmpty());
    }

    @Test
    void testGet() {
        MutableByteString str = new MutableByteString(new byte[]{1, 2, 3});
        assertEquals(1, str.get(0));
        assertEquals(2, str.get(1));
        assertEquals(3, str.get(2));

        assertThrows(IndexOutOfBoundsException.class, () -> str.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> str.get(3));
    }


    @Test
    void testSetBytes() {
        MutableByteString str = new MutableByteString(new byte[]{1, 2, 3});
        str.set(new byte[]{4, 5});
        assertArrayEquals(new byte[]{4, 5}, Arrays.copyOfRange(str.bytes, 0, str.length()));
        assertEquals(2, str.length());
    }

    @Test
    void testSetIndex() {
        MutableByteString str = new MutableByteString(new byte[]{1, 2, 3});
        str.set(1, (byte) 4);
        assertArrayEquals(new byte[]{1, 4, 3}, Arrays.copyOfRange(str.bytes, 0, str.length()));


        assertThrows(IndexOutOfBoundsException.class, () -> str.set(-1, (byte) 0));
        assertThrows(IndexOutOfBoundsException.class, () -> str.set(3, (byte) 0));
    }


    @Test
    void testAppend() {
        MutableByteString str = new MutableByteString();
        str.append((byte) 1);
        str.append((byte) 2);
        str.append((byte) 3);
        assertArrayEquals(new byte[]{1, 2, 3}, Arrays.copyOfRange(str.bytes, 0, str.length()));
        assertEquals(3, str.length());
    }

    @Test
    void testSubstring() {
        MutableByteString str = new MutableByteString(new byte[]{1, 2, 3, 4});
        MutableByteString sub = str.substring(1, 3);
        assertArrayEquals(new byte[]{2, 3}, Arrays.copyOfRange(sub.bytes, 0, sub.length()));
        assertEquals(2, sub.length());


    }

    @Test
    void testReplace() {
        MutableByteString str = new MutableByteString(new byte[]{1, 2, 1, 2});
        str.replace(new byte[]{1}, new byte[]{3, 4});
        assertArrayEquals(new byte[]{3, 4, 2, 3, 4, 2}, Arrays.copyOfRange(str.bytes, 0, str.length()));



        str = new MutableByteString(new byte[]{1, 2, 1, 2});
        str.replace(new byte[]{}, new byte[]{3});
        assertArrayEquals(new byte[]{3, 1, 3, 2, 3, 1, 3, 2, 3}, Arrays.copyOfRange(str.bytes, 0, str.length()));


    }


    @Test
    void testRegionEquals() {
        MutableByteString str1 = new MutableByteString(new byte[]{1, 2, 3});
        MutableByteString str2 = new MutableByteString(new byte[]{0, 1, 2, 3, 4});
        assertTrue(str1.regionEquals(0, str2, 1, 3));
        assertFalse(str1.regionEquals(0, str2, 0, 3));
    }


    @Test
    void testToHexString() {
        MutableByteString str = new MutableByteString(new byte[]{10, 11, 12}); // a, b, c in hex
        assertEquals("0a0b0c", str.toHexString());


    }


    @Test
    void testToStringUtf8() {
        String original = "hello";
        byte[] bytes = original.getBytes(StandardCharsets.UTF_8);
        MutableByteString str = new MutableByteString(bytes);
        assertEquals(original, str.toStringUtf8());


    }




}

