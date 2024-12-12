import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.math.BigInteger

class LeftFactorialTest {

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
        assertEquals(BigInteger.TWO, leftFactorial(2))
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
    fun testLeftFactorialTen() {
        assertEquals(BigInteger.valueOf(4037913), leftFactorial(10))
    }

    @Test
    fun testLeftFactorialTwenty() {
        assertEquals(BigInteger("265252859812191058636308480000000"), leftFactorial(20))
    }
    
    @Test
    fun testLeftFactorialLargeNumberLength() {
        val expectedLength = 6487
        val actualLength = leftFactorial(10000).toString().length
        assertEquals(expectedLength, actualLength)
    }



}
