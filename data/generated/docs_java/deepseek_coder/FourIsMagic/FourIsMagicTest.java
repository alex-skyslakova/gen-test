import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FourIsMagicTest {

    @Test
    public void testFourIsMagic() {
        assertEquals("Four is magic.", FourIsMagic.fourIsMagic(4));
    }

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
    public void testTwenty() {
        assertEquals("Twenty is six, six is three, three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(20));
    }

    @Test
    public void testThirty() {
        assertEquals("Thirty is six, six is three, three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(30));
    }

    @Test
    public void testHundred() {
        assertEquals("One hundred is eleven, eleven is six, six is three, three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(100));
    }

    @Test
    public void testThousand() {
        assertEquals("One thousand is eleven, eleven is six, six is three, three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(1000));
    }

    @Test
    public void testMillion() {
        assertEquals("One million is ten, ten is three, three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(1000000));
    }

    @Test
    public void testBillion() {
        assertEquals("One billion is ten, ten is three, three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(1000000000));
    }

    @Test
    public void testTrillion() {
        assertEquals("One trillion is ten, ten is three, three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(1000000000000L));
    }

    @Test
    public void testNegativeSeven() {
        assertEquals("Negative seven is eleven, eleven is six, six is three, three is five, five is four, four is magic.", FourIsMagic.fourIsMagic(-7));
    }

    @Test
    public void testMaxValue() {
        assertEquals("Nine quintillion two hundred twenty-three trillion three hundred seventy-two billion thirty-six million eight hundred fifty-four thousand seven hundred seventy-five is one hundred twenty-five, one hundred twenty-five is twenty-one, twenty-one is nine, nine is four, four is magic.", FourIsMagic.fourIsMagic(Long.MAX_VALUE));
    }
}
