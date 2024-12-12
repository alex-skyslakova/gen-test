import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class LeftFactorialsTest {

    @Test
    fun testLeftFactorialZero() {
        assertEquals(BigInteger.ZERO, leftFactorial(0))
    }

    @Test
    fun testLeftFactorialOne() {
        assertEquals(BigInteger.ONE, leftFactorial(1))
    }

    @Test
    fun testLeftFactorialTwo() {
        assertEquals(BigInteger.valueOf(2), leftFactorial(2))
    }

    @Test
    fun testLeftFactorialThree() {
        assertEquals(BigInteger.valueOf(4), leftFactorial(3))
    }

    @Test
    fun testLeftFactorialFour() {
        assertEquals(BigInteger.valueOf(10), leftFactorial(4))
    }

    @Test
    fun testLeftFactorialFive() {
        assertEquals(BigInteger.valueOf(34), leftFactorial(5))
    }

    @Test
    fun testLeftFactorialTen() {
        assertEquals(BigInteger.valueOf(4037914), leftFactorial(10))
    }

    @Test
    fun testLeftFactorialTwenty() {
        assertEquals(BigInteger("128425485935180314"), leftFactorial(20))
    }

    @Test
    fun testLeftFactorialThirty() {
        assertEquals(BigInteger("900132073808889736"), leftFactorial(30))
    }

    @Test
    fun testLeftFactorialForty() {
        assertEquals(BigInteger("2093505108241777184"), leftFactorial(40))
    }

    @Test
    fun testLeftFactorialFifty() {
        assertEquals(BigInteger("6209600278328216126"), leftFactorial(50))
    }

    @Test
    fun testLeftFactorialSixty() {
        assertEquals(BigInteger("1904201759154492646"), leftFactorial(60))
    }

    @Test
    fun testLeftFactorialSeventy() {
        assertEquals(BigInteger("6188655882963239517"), leftFactorial(70))
    }

    @Test
    fun testLeftFactorialEighty() {
        assertEquals(BigInteger("2132710128735490039"), leftFactorial(80))
    }

    @Test
    fun testLeftFactorialNinety() {
        assertEquals(BigInteger("7579515417749261828"), leftFactorial(90))
    }

    @Test
    fun testLeftFactorialHundred() {
        assertEquals(BigInteger("2837657501254960389"), leftFactorial(100))
    }

    @Test
    fun testLeftFactorialThousand() {
        val expectedDigits = 2568
        assertEquals(expectedDigits, leftFactorial(1000).toString().length)
    }

    @Test
    fun testLeftFactorialTwoThousand() {
        val expectedDigits = 5736
        assertEquals(expectedDigits, leftFactorial(2000).toString().length)
    }

    @Test
    fun testLeftFactorialThreeThousand() {
        val expectedDigits = 9128
        assertEquals(expectedDigits, leftFactorial(3000).toString().length)
    }

    @Test
    fun testLeftFactorialFourThousand() {
        val expectedDigits = 12670
        assertEquals(expectedDigits, leftFactorial(4000).toString().length)
    }

    @Test
    fun testLeftFactorialFiveThousand() {
        val expectedDigits = 16322
        assertEquals(expectedDigits, leftFactorial(5000).toString().length)
    }

    @Test
    fun testLeftFactorialSixThousand() {
        val expectedDigits = 20062
        assertEquals(expectedDigits, leftFactorial(6000).toString().length)
    }

    @Test
    fun testLeftFactorialSevenThousand() {
        val expectedDigits = 23875
        assertEquals(expectedDigits, leftFactorial(7000).toString().length)
    }

    @Test
    fun testLeftFactorialEightThousand() {
        val expectedDigits = 27749
        assertEquals(expectedDigits, leftFactorial(8000).toString().length)
    }

    @Test
    fun testLeftFactorialNineThousand() {
        val expectedDigits = 31678
        assertEquals(expectedDigits, leftFactorial(9000).toString().length)
    }

    @Test
    fun testLeftFactorialTenThousand() {
        val expectedDigits = 35656
        assertEquals(expectedDigits, leftFactorial(10000).toString().length)
    }
}
