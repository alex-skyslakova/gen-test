import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LeftTruncatablePrimeTest {

    @Test
    fun testNextLeftTruncatablePrimesEmpty() {
        val result = nextLeftTruncatablePrimes(BigInteger.ZERO, 3, 10)
        assertEquals(listOf(BigInteger.valueOf(2L)), result)
    }

    @Test
    fun testNextLeftTruncatablePrimesBase3() {
        val result = nextLeftTruncatablePrimes(BigInteger.valueOf(2L), 3, 10)
        assertEquals(listOf(BigInteger.valueOf(5L)), result)


        val result2 = nextLeftTruncatablePrimes(BigInteger.valueOf(5L), 3,10)
        assertEquals(listOf<BigInteger>(), result2)

        val result3 = nextLeftTruncatablePrimes(BigInteger.valueOf(1L), 10,10)
        assertEquals(listOf(BigInteger.valueOf(2L), BigInteger.valueOf(3L), BigInteger.valueOf(5L),BigInteger.valueOf(7L)), result3.sorted())
    }


    @Test
    fun testNextLeftTruncatablePrimesBase10() {
        val result = nextLeftTruncatablePrimes(BigInteger.valueOf(3L), 10, 10)
        val expected = listOf(BigInteger.valueOf(13L), BigInteger.valueOf(23L), BigInteger.valueOf(43L), BigInteger.valueOf(53L), BigInteger.valueOf(73L), BigInteger.valueOf(83L))
        assertEquals(expected.sorted(), result.sorted())
    }


    @Test
    fun testLargestLeftTruncatablePrimeBase3() {
        val result = largestLeftTruncatablePrime(3, 10)
        assertEquals(BigInteger.valueOf(5L), result)
    }

    @Test
    fun testLargestLeftTruncatablePrimeBase2() {
        val result = largestLeftTruncatablePrime(2, 10)
        assertNull(result)
    }


    @Test
    fun testLargestLeftTruncatablePrimeBase10() {
        val result = largestLeftTruncatablePrime(10, 10)
        assertEquals(BigInteger.valueOf(357686312646216567629137L), result)
    }


      @Test
    fun testLargestLeftTruncatablePrimeBase5() {
        val result = largestLeftTruncatablePrime(5, 10)
        assertEquals(BigInteger.valueOf(2421L), result)
    }


    // Add more tests for different base and certainty values as needed.
    // For instance:
     //@Test
    // fun testLargestLeftTruncatablePrimeBase7() {
    //     val result = largestLeftTruncatablePrime(7, 10)
    //     assertEquals(BigInteger.valueOf(66431L), result) // Example value, verify if correct
    // }
}



