import org.junit.Test
import kotlin.test.assertEquals

class FourIsMagicTest {

    @Test
    fun testZero() {
        assertEquals("Zero is four, four is magic.", fourIsMagic(0))
    }

    @Test
    fun testFour() {
        assertEquals("Four is magic.", fourIsMagic(4))
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
    fun testThirteen() {
        assertEquals("Thirteen is eight, eight is five, five is four, four is magic.", fourIsMagic(13))
    }

    @Test
    fun testSeventyFive() {
        assertEquals("Seventy-five is eleven, eleven is six, six is three, three is five, five is four, four is magic.", fourIsMagic(75))
    }

    @Test
    fun testOneHundred() {
        assertEquals("One hundred is eleven, eleven is six, six is three, three is five, five is four, four is magic.", fourIsMagic(100))
    }

    @Test
    fun testThreeHundredThirtySeven() {
        assertEquals("Three hundred thirty-seven is twenty-six, twenty-six is ten, ten is three, three is five, five is four, four is magic.", fourIsMagic(337))
    }

    @Test
    fun testNegativeOneHundredSixtyFour() {
        assertEquals("Negative one hundred sixty-four is twenty-five, twenty-five is ten, ten is three, three is five, five is four, four is magic.", fourIsMagic(-164))
    }

    @Test
    fun testMaxLong() {
        assertEquals("Nine quintillion two hundred twenty-three quadrillion three hundred seventy-two trillion thirty-six billion eight hundred fifty-four million seven hundred seventy-five thousand eight hundred seven is one hundred sixty-six, one hundred sixty-six is twenty-two, twenty-two is ten, ten is three, three is five, five is four, four is magic.", fourIsMagic(9_223_372_036_854_775_807L))
    }
}
