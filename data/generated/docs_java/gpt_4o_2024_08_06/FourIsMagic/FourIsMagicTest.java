import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FourIsMagicTest {

    @Test
    public void testZero() {
        assertEquals("Zero is four, four is magic.", FourIsMagic.fourIsMagic(0));
    }

    @Test
    public void testOne() {
        assertEquals("One is three, three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(1));
    }

    @Test
    public void testTwo() {
        assertEquals("Two is three, three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(2));
    }

    @Test
    public void testThree() {
        assertEquals("Three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(3));
    }

    @Test
    public void testFour() {
        assertEquals("Four is magic.", FourIsMagic.fourIsMagic(4));
    }

    @Test
    public void testFive() {
        assertEquals("Five is four, four is magic.", FourIsMagic.fourIsMagic(5));
    }

    @Test
    public void testSix() {
        assertEquals("Six is three, three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(6));
    }

    @Test
    public void testSeven() {
        assertEquals("Seven is five, five is four, four is magic.", FourIsMagic.fourIsMagic(7));
    }

    @Test
    public void testEight() {
        assertEquals("Eight is five, five is four, four is magic.", FourIsMagic.fourIsMagic(8));
    }

    @Test
    public void testNine() {
        assertEquals("Nine is four, four is magic.", FourIsMagic.fourIsMagic(9));
    }

    @Test
    public void testTen() {
        assertEquals("Ten is three, three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(10));
    }

    @Test
    public void testTwentyThree() {
        assertEquals("Twenty three is eleven, eleven is six, six is three, three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(23));
    }

    @Test
    public void testOneHundred() {
        assertEquals("One hundred is ten, ten is three, three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(100));
    }

    @Test
    public void testNegativeSeven() {
        assertEquals("Negative seven is thirteen, thirteen is eight, eight is five, five is four, four is magic.", FourIsMagic.fourIsMagic(-7));
    }

    @Test
    public void testLargeNumber() {
        assertEquals("Two hundred forty six thousand five hundred seventy one is thirty six, thirty six is nine, nine is four, four is magic.", FourIsMagic.fourIsMagic(246571));
    }
}
