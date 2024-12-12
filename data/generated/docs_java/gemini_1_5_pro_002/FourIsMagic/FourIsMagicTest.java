import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FourIsMagicTest {

    @Test
    void testFourIsMagic_4() {
        assertEquals("four is magic", FourIsMagic.fourIsMagic(4));
    }

    @Test
    void testFourIsMagic_0() {
        assertEquals("zero is four, four is magic", FourIsMagic.fourIsMagic(0));
    }

    @Test
    void testFourIsMagic_1() {
        assertEquals("one is three, three is five, five is four, four is magic", FourIsMagic.fourIsMagic(1));
    }

    @Test
    void testFourIsMagic_2() {
        assertEquals("two is three, three is five, five is four, four is magic", FourIsMagic.fourIsMagic(2));
    }

    @Test
    void testFourIsMagic_3() {
        assertEquals("three is five, five is four, four is magic", FourIsMagic.fourIsMagic(3));
    }

    @Test
    void testFourIsMagic_5() {
        assertEquals("five is four, four is magic", FourIsMagic.fourIsMagic(5));
    }
    
    @Test
    void testFourIsMagic_21() {
        assertEquals("twenty one is nine, nine is four, four is magic", FourIsMagic.fourIsMagic(21));
    }

    @Test
    void testFourIsMagic_100() {
        assertEquals("one hundred is eleven, eleven is six, six is three, three is five, five is four, four is magic", FourIsMagic.fourIsMagic(100));
    }

    @Test
    void testFourIsMagic_1234() {
        String expected = "one thousand two hundred thirty four is twenty three, twenty three is ten, ten is three, three is five, five is four, four is magic";
        assertEquals(expected, FourIsMagic.fourIsMagic(1234));
    }


    @Test
    void testFourIsMagic_largeNumber() {
        String expectedPrefix = "one quintillion two hundred thirty four quadrillion five hundred sixty seven trillion eight hundred ninety billion one hundred twenty three million four hundred fifty six thousand seven hundred eighty nine is ";
        String expected = expectedPrefix + "one hundred thirty nine, one hundred thirty nine is sixteen, sixteen is seven, seven is five, five is four, four is magic";
        assertEquals(expected, FourIsMagic.fourIsMagic(1234567890123456789L));
    }
    
    @Test
    void testFourIsMagic_negative() {
        String expected = "negative seven is twelve, twelve is six, six is three, three is five, five is four, four is magic";
        assertEquals(expected, FourIsMagic.fourIsMagic(-7));
    }

    @Test
    void testNumToString_0() {
        assertEquals("zero", FourIsMagic.numToString(0));
    }

    @Test
    void testNumToString_19() {
        assertEquals("nineteen", FourIsMagic.numToString(19));
    }

    @Test
    void testNumToString_20() {
        assertEquals("twenty", FourIsMagic.numToString(20));
    }
    
    @Test
    void testNumToString_21() {
        assertEquals("twenty one", FourIsMagic.numToString(21));
    }


    @Test
    void testNumToString_99() {
        assertEquals("ninety nine", FourIsMagic.numToString(99));
    }

    @Test
    void testNumToString_100() {
        assertEquals("one hundred", FourIsMagic.numToString(100));
    }
        
    @Test
    void testNumToString_LongMaxValue() {
        assertEquals("nine quintillion two hundred twenty three quadrillion three hundred seventy two trillion thirty six billion eight hundred fifty four million seven hundred seventy five thousand eight hundred seven", FourIsMagic.numToString(Long.MAX_VALUE));

    }



}
