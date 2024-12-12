import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class FourIsMagicTest {

    @Test
    fun testZero() {
        assertEquals("Zero is four, four is magic.", fourIsMagic(0))
    }

    @Test
    fun testOne() {
        assertEquals("One is three, three is five, five is four, four is magic.", fourIsMagic(1))
    }

    @Test
    fun testTwo() {
        assertEquals("Two is three, three is five, five is four, four is magic.", fourIsMagic(2))
    }

    @Test
    fun testThree() {
        assertEquals("Three is five, five is four, four is magic.", fourIsMagic(3))
    }

    @Test
    fun testFour() {
        assertEquals("Four is magic.", fourIsMagic(4))
    }

    @Test
    fun testFive() {
        assertEquals("Five is four, four is magic.", fourIsMagic(5))
    }

    @Test
    fun testSix() {
        assertEquals("Six is three, three is five, five is four, four is magic.", fourIsMagic(6))
    }

    @Test
    fun testSeven() {
        assertEquals("Seven is five, five is four, four is magic.", fourIsMagic(7))
    }

    @Test
    fun testEight() {
        assertEquals("Eight is five, five is four, four is magic.", fourIsMagic(8))
    }

    @Test
    fun testNine() {
        assertEquals("Nine is four, four is magic.", fourIsMagic(9))
    }

    @Test
    fun testEleven() {
        assertEquals("Eleven is six, six is three, three is five, five is four, four is magic.", fourIsMagic(11))
    }

    @Test
    fun testTwenty() {
        assertEquals("Twenty is six, six is three, three is five, five is four, four is magic.", fourIsMagic(20))
    }
    
    @Test
    fun testTwentyThree() {
        assertEquals("Twenty-three is eleven, eleven is six, six is three, three is five, five is four, four is magic.", fourIsMagic(23))
    }


    @Test
    fun testOneHundred() {
        assertEquals("One hundred is ten, ten is three, three is five, five is four, four is magic.", fourIsMagic(100))
    }

    @Test
    fun testLargeNumber() {
        assertEquals("Nine quintillion two hundred twenty-three quadrillion three hundred seventy-two trillion thirty-six billion eight hundred fifty-four million seven hundred seventy-five thousand eight hundred seven is ninety-nine, ninety-nine is eight, eight is five, five is four, four is magic.", fourIsMagic(9_223_372_036_854_775_807L))
    }

    @Test
    fun testNegativeNumber() {
        assertEquals("Negative one hundred sixty-four is nineteen, nineteen is eight, eight is five, five is four, four is magic.", fourIsMagic(-164))
    }

    @Test
    fun testLongMinValue() {
        // This test covers the edge case where n == Long.MIN_VALUE.  The original code has a bug
        // where it fails for this value.  This test should drive fixing that bug.
        val expected = "Negative nine quintillion two hundred twenty-three quadrillion three hundred seventy-two trillion thirty-six billion eight hundred fifty-four million seven hundred seventy-five thousand eight hundred eight is one hundred, one hundred is ten, ten is three, three is five, five is four, four is magic."
        assertEquals(expected, fourIsMagic(Long.MIN_VALUE))
    }
}
