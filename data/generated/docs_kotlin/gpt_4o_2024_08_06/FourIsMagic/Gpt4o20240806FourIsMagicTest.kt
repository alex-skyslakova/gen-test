import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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
    fun testTen() {
        assertEquals("Ten is three, three is five, five is four, four is magic.", fourIsMagic(10))
    }

    @Test
    fun testNegativeNumber() {
        assertEquals("Negative one hundred sixty-four is twenty-nine, twenty-nine is ten, ten is three, three is five, five is four, four is magic.", fourIsMagic(-164))
    }

    @Test
    fun testLargeNumber() {
        assertEquals("Nine quintillion two hundred twenty-three quadrillion three hundred seventy-two trillion thirty-six billion eight hundred fifty-four million seven hundred seventy-five thousand eight hundred seven is one hundred forty-two, one hundred forty-two is twenty-three, twenty-three is eleven, eleven is six, six is three, three is five, five is four, four is magic.", fourIsMagic(9_223_372_036_854_775_807L))
    }

    @Test
    fun testHundred() {
        assertEquals("One hundred is ten, ten is three, three is five, five is four, four is magic.", fourIsMagic(100))
    }

    @Test
    fun testThousand() {
        assertEquals("One thousand is eleven, eleven is six, six is three, three is five, five is four, four is magic.", fourIsMagic(1000))
    }
}
